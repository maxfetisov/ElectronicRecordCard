package diploma.electronicrecordcard.service.model.impl;

import diploma.electronicrecordcard.data.dto.model.RoleDto;
import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.dto.request.UserUpdateRequestDto;
import diploma.electronicrecordcard.data.entity.Role;
import diploma.electronicrecordcard.data.entity.User;
import diploma.electronicrecordcard.exception.entityalreadyexists.UserAlreadyExistsException;
import diploma.electronicrecordcard.exception.entitynotfound.UserNotFoundException;
import diploma.electronicrecordcard.exception.versionconflict.UserVersionConflictException;
import diploma.electronicrecordcard.repository.model.UserRepository;
import diploma.electronicrecordcard.service.model.UserService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import diploma.electronicrecordcard.util.EntitySpecifications;
import diploma.electronicrecordcard.util.VersionUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    Mapper<UserDto, User> userMapper;

    Mapper<RoleDto, Role> roleMapper;

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public List<UserDto> getTeachers() {
        return List.of();
    }

    @Override
    public List<UserDto> getStudents() {
        return List.of();
    }

    @Override
    public UserDto getById(Long id) {
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString())));
    }

    @Override
    public UserDto getByLogin(String login) {
        return userMapper.toDto(userRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException("login", login)));
    }

    @Override
    public List<RoleDto> getUserRoles(Long id) {
        return userRepository.findRolesByUserId(id).stream()
                .map(roleMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public UserDto create(UserDto userDto) {
        if (userRepository.existsByLogin(userDto.login())) {
            throw new UserAlreadyExistsException(userDto.login());
        }
        User user = userMapper.toEntity(userDto);
        user.setVersion(userRepository.getNextVersion());
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserDto update(UserUpdateRequestDto userDto) {
        User user = userRepository.findById(userDto.id())
                .orElseThrow(() -> new UserNotFoundException(userDto.id().toString()));
        if (!user.getLogin().equals(userDto.login()) && userRepository.existsByLogin(userDto.login())) {
            throw new UserAlreadyExistsException(userDto.login());
        }
        VersionUtil.checkVersionAndThrowVersionConflict(user, userDto, UserVersionConflictException.class);

        return userMapper.toDto(userRepository.save(userMapper.toEntity(UserDto.builder()
                .id(userDto.id())
                .login(userDto.login())
                .password(user.getPassword())
                .lastName(userDto.lastName())
                .firstName(userDto.firstName())
                .middleName(userDto.middleName())
                .phoneNumber(userDto.phoneNumber())
                .email(userDto.email())
                .recordBookNumber(userDto.recordBookNumber())
                .groupId(userDto.groupId())
                .instituteId(userDto.instituteId())
                .roles(userDto.roles())
                .deleted(user.getDeleted())
                .version(userRepository.getNextVersion())
                .build())));
    }

    @Override
    @Transactional
    public UserDto delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(id.toString()));
        user.setDeleted(true);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public List<UserDto> getByCriteria(Map<String, Object> criteria) {
        return getByCriteria(EntitySpecifications.getSpecification(criteria));
    }

    @Override
    public List<UserDto> getByCriteria(Map<String, Object> criteria, Long version) {
        var specification = EntitySpecifications.<User>getSpecification(criteria);
        var versionSpecification = VersionUtil.<User>getVersionSpecification(version);
        return getByCriteria(Optional.of(specification.map((spec) -> spec.and(versionSpecification))
                .orElse(versionSpecification)));
    }

    private List<UserDto> getByCriteria(Optional<Specification<User>> specification) {
        return specification.map(userRepository::findAll)
                .orElse(userRepository.findAll())
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

}
