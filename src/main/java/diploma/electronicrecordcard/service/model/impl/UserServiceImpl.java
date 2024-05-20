package diploma.electronicrecordcard.service.model.impl;

import diploma.electronicrecordcard.data.dto.model.RoleDto;
import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.dto.request.UserUpdateRequestDto;
import diploma.electronicrecordcard.data.entity.Group;
import diploma.electronicrecordcard.data.entity.Institute;
import diploma.electronicrecordcard.data.entity.Role;
import diploma.electronicrecordcard.data.entity.User;
import diploma.electronicrecordcard.data.enumeration.RoleName;
import diploma.electronicrecordcard.exception.entityalreadyexists.UserAlreadyExistsException;
import diploma.electronicrecordcard.exception.entitynotfound.GroupNotFoundException;
import diploma.electronicrecordcard.exception.entitynotfound.InstituteNotFoundException;
import diploma.electronicrecordcard.exception.entitynotfound.UserNotFoundException;
import diploma.electronicrecordcard.exception.illegalvalue.IllegalValueException;
import diploma.electronicrecordcard.exception.noauthority.NoAuthorityException;
import diploma.electronicrecordcard.exception.versionconflict.UserVersionConflictException;
import diploma.electronicrecordcard.repository.model.RoleRepository;
import diploma.electronicrecordcard.repository.model.UserRepository;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.criteria.CriteriaService;
import diploma.electronicrecordcard.service.model.DeletionService;
import diploma.electronicrecordcard.service.model.UserService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import diploma.electronicrecordcard.util.EntitySpecifications;
import diploma.electronicrecordcard.util.VersionUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static diploma.electronicrecordcard.data.enumeration.EntityType.USER;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    Mapper<UserDto, User> userMapper;

    Mapper<RoleDto, Role> roleMapper;

    AuthorityService authorityService;

    CriteriaService<User> userCriteriaService;

    DeletionService deletionService;

    CriteriaService<Institute> instituteCriteriaService;

    CriteriaService<Group> groupCriteriaService;
    private final RoleRepository roleRepository;

    @Override
    public List<UserDto> getAll() {
        return getByCriteria(Map.of());
    }

    @Override
    public UserDto getById(Long id) {
        return getByCriteria(Map.of("id", id)).stream().findFirst()
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
    }

    @Override
    public UserDto getByLogin(String login) {
        return getByCriteria(Map.of("login", login)).stream().findFirst()
                .orElseThrow(() -> new UserNotFoundException("login", login));
    }

    @Override
    public List<RoleDto> getUserRoles(Long id) {
        authorityService.checkRolesAndThrow(List.of(RoleName.ADMINISTRATOR));
        return userRepository.findRolesByUserId(id).stream()
                .map(roleMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserDto create(UserDto userDto) {
        authorityService.checkRolesAndThrow(List.of(RoleName.DEAN_OFFICE_EMPLOYEE, RoleName.ADMINISTRATOR));
        var roles = getRolesByIds(userDto.roles());
        User user = userMapper.toEntity(userDto);
        if (roles.contains(RoleName.STUDENT) && nonNull(userDto.groupId()) && isNull(userDto.instituteId())) {
            var istituteList = instituteCriteriaService.getByCriteria(EntitySpecifications
                    .<Institute>getSpecification(Map.of("groups.id", userDto.groupId()))
                    .orElse(null));
            if (!istituteList.isEmpty()) {
                user.setInstitute(istituteList.getFirst());
            }
        }
        checkConstraints(userMapper.toDto(user));
        user.setVersion(userRepository.getNextVersion());
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserDto update(UserUpdateRequestDto userDto) {
        authorityService.checkRolesAndThrow(List.of(RoleName.DEAN_OFFICE_EMPLOYEE, RoleName.ADMINISTRATOR));
        User user = userRepository.findById(userDto.id())
                .orElseThrow(() -> new UserNotFoundException(userDto.id().toString()));
        VersionUtil.checkVersionAndThrowVersionConflict(user, userDto, UserVersionConflictException.class);
        checkRightsConstraint(userMapper.toDto(user));
        var updatedUser = UserDto.builder()
                .id(userDto.id())
                .login(nonNull(userDto.login()) ? userDto.login() : user.getLogin())
                .password(user.getPassword())
                .lastName(nonNull(userDto.lastName()) ? userDto.lastName() : user.getLastName())
                .firstName(nonNull(userDto.firstName()) ? userDto.firstName() : user.getFirstName())
                .middleName(nonNull(userDto.middleName()) ? userDto.middleName() : user.getMiddleName())
                .phoneNumber(nonNull(userDto.phoneNumber()) ? userDto.phoneNumber() : user.getPhoneNumber())
                .email(nonNull(userDto.email()) ? userDto.email() : user.getEmail())
                .recordBookNumber(nonNull(userDto.recordBookNumber())
                        ? userDto.recordBookNumber() : user.getRecordBookNumber())
                .groupId(nonNull(userDto.groupId()) ? userDto.groupId() : user.getGroup().getId())
                .instituteId(nonNull(userDto.instituteId()) ? userDto.instituteId() : user.getInstitute().getId())
                .roles(nonNull(userDto.roles()) ? userDto.roles() : user.getRoles().stream().map(Role::getId).toList())
                .deleted(user.getDeleted())
                .version(userRepository.getNextVersion())
                .build();
        checkConstraints(updatedUser);
        return userMapper.toDto(userRepository.save(userMapper.toEntity(updatedUser)));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserDto delete(Long id, Long version) {
        authorityService.checkRolesAndThrow(List.of(RoleName.DEAN_OFFICE_EMPLOYEE, RoleName.ADMINISTRATOR));
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(id.toString()));
        VersionUtil.checkVersionAndThrowVersionConflict(user, () -> version, UserVersionConflictException.class);
        checkRightsConstraint(userMapper.toDto(user));
        user.setDeleted(true);
        user.setVersion(userRepository.getNextVersion());
        deletionService.create(USER, id);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public List<UserDto> getByCriteria(Map<String, Object> criteria) {
        return userCriteriaService.getByCriteria(EntitySpecifications.<User>getSpecification(criteria)
                        .orElse(null)).stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public List<UserDto> getByCriteria(Map<String, Object> criteria, Long version) {
        var specification = EntitySpecifications.<User>getSpecification(criteria);
        var versionSpecification = VersionUtil.<User>getVersionSpecification(version);
        return userCriteriaService.getByCriteria(specification.map((spec)
                        -> spec.and(versionSpecification)).orElse(versionSpecification)).stream()
                .map(userMapper::toDto)
                .toList();
    }

    private void checkConstraints(UserDto user) {
        checkExistenceConstraint(user);
        checkRightsConstraint(user);
    }

    private void checkExistenceConstraint(UserDto user) {
        var optionalUser = userRepository.findByLogin(user.login());
        if (optionalUser.isPresent()) {
            var dbUser = optionalUser.get();
            if (!Objects.equals(dbUser.getId(), user.id())) {
                if (Objects.equals(dbUser.getLogin(), user.login())) {
                    throw new UserAlreadyExistsException("login", user.login());
                }
            }
        }
        if (nonNull(user.recordBookNumber())) {
            optionalUser = userRepository.findByRecordBookNumber(user.recordBookNumber());
            if (optionalUser.isPresent()) {
                var dbUser = optionalUser.get();
                if (!Objects.equals(dbUser.getId(), user.id())) {
                    if (Objects.equals(dbUser.getRecordBookNumber(), user.recordBookNumber())) {
                        throw new UserAlreadyExistsException("Номер зачетной книжки", user.recordBookNumber());
                    }
                }
            }
        }
        var roles = getRolesByIds(user.roles());
        if (roles.isEmpty()) {
            throw new IllegalValueException("roles", roles.toString());
        }
        if (roles.contains(RoleName.STUDENT) && isNull(user.groupId())) {
            throw new IllegalValueException("groupId", null);
        }
        if (roles.contains(RoleName.STUDENT) && isNull(user.recordBookNumber())) {
            throw new IllegalValueException("recordBookNumber", null);
        }
        if (!roles.contains(RoleName.ADMINISTRATOR) && isNull(user.instituteId())) {
            throw new IllegalValueException("instituteId", null);
        }
        if (nonNull(user.groupId())) {
            var groupList = groupCriteriaService.getByCriteria(EntitySpecifications
                    .<Group>getSpecification(Map.of("id", user.groupId()))
                    .orElse(null));
            if (groupList.isEmpty()) {
                throw new GroupNotFoundException(user.groupId().toString());
            }
        }
        if (nonNull(user.instituteId())) {
            var instituteList = instituteCriteriaService.getByCriteria(EntitySpecifications
                    .<Institute>getSpecification(Map.of("id", user.instituteId()))
                    .orElse(null));
            if (instituteList.isEmpty()) {
                throw new InstituteNotFoundException(user.instituteId().toString());
            }
        }
    }

    private void checkRightsConstraint(UserDto user) {
        if (authorityService.hasAnyAuthority(List.of(RoleName.ADMINISTRATOR))) {
            return;
        }
        var currentUser = authorityService.getCurrentUser();
        if (!Objects.equals(currentUser.instituteId(), user.instituteId())) {
            throw new NoAuthorityException();
        }
        if(nonNull(user.groupId())) {
            var groupList = groupCriteriaService.getByCriteria(EntitySpecifications
                    .<Group>getSpecification(Map.of(
                            "id", user.groupId(),
                            "institute.id", user.instituteId()
                    ))
                    .orElse(null));
            if (groupList.isEmpty()) {
                throw new NoAuthorityException();
            }
        }
        var roles = getRolesByIds(user.roles());
        if (roles.contains(RoleName.DEAN_OFFICE_EMPLOYEE) || roles.contains(RoleName.ADMINISTRATOR)) {
            throw new NoAuthorityException();
        }
    }

    private List<RoleName> getRolesByIds(List<Short> roleIds) {
        return roleRepository.findAll().stream()
                .filter(role -> roleIds.contains(role.getId()))
                .map(role -> RoleName.valueOf(role.getName()))
                .toList();
    }
}
