package diploma.electronicrecordcard.service.model.impl;

import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.dto.model.UserSubjectControlTypeDto;
import diploma.electronicrecordcard.data.dto.request.UserSubjectControlTypeCreateByGroupRequestDto;
import diploma.electronicrecordcard.data.dto.request.UserSubjectControlTypeCreateRequestDto;
import diploma.electronicrecordcard.data.entity.Subject;
import diploma.electronicrecordcard.data.entity.User;
import diploma.electronicrecordcard.data.entity.UserSubjectControlType;
import diploma.electronicrecordcard.data.enumeration.RoleName;
import diploma.electronicrecordcard.exception.entitynotfound.ControlTypeNotFoundException;
import diploma.electronicrecordcard.exception.entitynotfound.SubjectNotFoundException;
import diploma.electronicrecordcard.exception.entitynotfound.UserNotFoundException;
import diploma.electronicrecordcard.exception.entitynotfound.UserSubjectControlTypeNotFoundException;
import diploma.electronicrecordcard.exception.versionconflict.UserSubjectControlTypeVersionConflictException;
import diploma.electronicrecordcard.repository.model.ControlTypeRepository;
import diploma.electronicrecordcard.repository.model.UserSubjectControlTypeRepository;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.criteria.CriteriaService;
import diploma.electronicrecordcard.service.model.DeletionService;
import diploma.electronicrecordcard.service.model.UserService;
import diploma.electronicrecordcard.service.model.UserSubjectControlTypeService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import diploma.electronicrecordcard.util.EntitySpecifications;
import diploma.electronicrecordcard.util.VersionUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static diploma.electronicrecordcard.data.enumeration.EntityType.USER_SUBJECT_CONTROL_TYPE;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserSubjectControlTypeServiceImpl implements UserSubjectControlTypeService {

    UserSubjectControlTypeRepository userSubjectControlTypeRepository;

    Mapper<UserSubjectControlTypeDto, UserSubjectControlType> userSubjectControlTypeMapper;

    DeletionService deletionService;

    AuthorityService authorityService;

    UserService userService;

    CriteriaService<UserSubjectControlType> userSubjectControlTypeCriteriaService;

    CriteriaService<Subject> subjectCriteriaService;

    CriteriaService<User> userCriteriaService;

    ControlTypeRepository controlTypeRepository;

    @Override
    public List<UserSubjectControlTypeDto> getAll() {
        return getByCriteria(Map.of());
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserSubjectControlTypeDto create(UserSubjectControlTypeCreateRequestDto userSubjectControlTypeDto) {
        authorityService.checkRolesAndThrow(List.of(RoleName.DEAN_OFFICE_EMPLOYEE, RoleName.ADMINISTRATOR));
        var userSubjectControlType = UserSubjectControlTypeDto.builder()
                .teacherId(userSubjectControlTypeDto.teacherId())
                .studentId(userSubjectControlTypeDto.studentId())
                .subjectId(userSubjectControlTypeDto.subjectId())
                .controlTypeId(userSubjectControlTypeDto.controlTypeId())
                .semester(userSubjectControlTypeDto.semester())
                .hoursNumber(userSubjectControlTypeDto.hoursNumber())
                .version(userSubjectControlTypeRepository.getNextVersion())
                .build();
        checkExistenceConstraint(userSubjectControlType);
        return userSubjectControlTypeMapper.toDto(userSubjectControlTypeRepository.save(
                userSubjectControlTypeMapper.toEntity(userSubjectControlType)));
    }

    @Override
    public List<UserSubjectControlTypeDto> create(UserSubjectControlTypeCreateByGroupRequestDto userSubjectControlTypeDto) {
        authorityService.checkRolesAndThrow(List.of(RoleName.DEAN_OFFICE_EMPLOYEE, RoleName.ADMINISTRATOR));
        List<UserDto> users = userService.getByCriteria(Map.of("group.id", userSubjectControlTypeDto.groupId()));
        List<UserSubjectControlTypeDto> userSubjectControlTypes = new ArrayList<>();
        for (var user : users) {
            var userSubjectControlType = UserSubjectControlTypeDto.builder()
                    .subjectId(userSubjectControlTypeDto.subjectId())
                    .teacherId(userSubjectControlTypeDto.teacherId())
                    .controlTypeId(userSubjectControlTypeDto.controlTypeId())
                    .hoursNumber(userSubjectControlTypeDto.hoursNumber())
                    .semester(userSubjectControlTypeDto.semester())
                    .studentId(user.id())
                    .version(userSubjectControlTypeRepository.getNextVersion())
                    .build();
            checkExistenceConstraint(userSubjectControlType);
            userSubjectControlTypes.add(userSubjectControlType);
        }
        return userSubjectControlTypeRepository.saveAll(userSubjectControlTypes.stream()
                        .map(userSubjectControlTypeMapper::toEntity)
                        .toList()).stream()
                .map(userSubjectControlTypeMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserSubjectControlTypeDto update(UserSubjectControlTypeDto userSubjectControlTypeDto) {
        authorityService.checkRolesAndThrow(List.of(RoleName.DEAN_OFFICE_EMPLOYEE, RoleName.ADMINISTRATOR));
        UserSubjectControlType userSubjectControlType = userSubjectControlTypeRepository
                .findById(userSubjectControlTypeDto.id())
                .orElseThrow(()
                        -> new UserSubjectControlTypeNotFoundException(userSubjectControlTypeDto.id().toString()));
        VersionUtil.checkVersionAndThrowVersionConflict(userSubjectControlType,
                userSubjectControlTypeDto,
                UserSubjectControlTypeVersionConflictException.class);
        checkRightConstraints(userSubjectControlTypeMapper.toDto(userSubjectControlType));
        checkConstraints(userSubjectControlTypeDto);
        UserSubjectControlType newUserSubjectControlType = userSubjectControlTypeMapper
                .toEntity(userSubjectControlTypeDto);
        newUserSubjectControlType.setVersion(userSubjectControlTypeRepository.getNextVersion());
        return userSubjectControlTypeMapper.toDto(userSubjectControlTypeRepository
                .save(newUserSubjectControlType));
    }

    @Override
    @Transactional
    public void delete(Long id, Long version) {
        authorityService.checkRolesAndThrow(List.of(RoleName.DEAN_OFFICE_EMPLOYEE, RoleName.ADMINISTRATOR));
        UserSubjectControlType userSubjectControlType = userSubjectControlTypeRepository.findById(id)
                .orElseThrow(() -> new UserSubjectControlTypeNotFoundException(id.toString()));
        VersionUtil.checkVersionAndThrowVersionConflict(userSubjectControlType,
                () -> version,
                UserSubjectControlTypeVersionConflictException.class);
        checkRightConstraints(userSubjectControlTypeMapper.toDto(userSubjectControlType));
        userSubjectControlTypeRepository.deleteById(id);
        deletionService.create(USER_SUBJECT_CONTROL_TYPE, id);

    }

    @Override
    public List<UserSubjectControlTypeDto> getByCriteria(Map<String, Object> criteria) {
        return userSubjectControlTypeCriteriaService.getByCriteria(EntitySpecifications
                        .<UserSubjectControlType>getSpecification(criteria)
                        .orElse(null)).stream()
                .map(userSubjectControlTypeMapper::toDto)
                .toList();
    }

    @Override
    public List<UserSubjectControlTypeDto> getByCriteria(Map<String, Object> criteria, Long version) {
        var specification = EntitySpecifications.<UserSubjectControlType>getSpecification(criteria);
        var versionSpecification = VersionUtil.<UserSubjectControlType>getVersionSpecification(version);
        return userSubjectControlTypeCriteriaService.getByCriteria(specification.map((spec)
                        -> spec.and(versionSpecification)).orElse(versionSpecification)).stream()
                .map(userSubjectControlTypeMapper::toDto)
                .toList();
    }

    private void checkConstraints(UserSubjectControlTypeDto userSubjectControlType) {
        checkExistenceConstraint(userSubjectControlType);
        checkRightConstraints(userSubjectControlType);
    }

    private void checkExistenceConstraint(UserSubjectControlTypeDto userSubjectControlType) {
        if(!controlTypeRepository.existsById(userSubjectControlType.controlTypeId())) {
            throw new ControlTypeNotFoundException(userSubjectControlType.controlTypeId().toString());
        }
        var subjectList = subjectCriteriaService.getByCriteria(EntitySpecifications.getSpecification(
                "id",
                userSubjectControlType.subjectId()
        ));
        if(subjectList.isEmpty()) {
            throw new SubjectNotFoundException(userSubjectControlType.subjectId().toString());
        }
        var studentList = userCriteriaService.getByCriteria(EntitySpecifications.<User>getSpecification(
                "id",
                userSubjectControlType.studentId()
        ).and(EntitySpecifications.getSpecification(
                "roles.name",
                RoleName.STUDENT.name()
        )));
        if(studentList.isEmpty()) {
            throw new UserNotFoundException(userSubjectControlType.studentId().toString());
        }
        var teacherList = userCriteriaService.getByCriteria(EntitySpecifications.<User>getSpecification(
                "id",
                userSubjectControlType.teacherId()
        ).and(EntitySpecifications.getSpecification(
                "roles.name",
                RoleName.TEACHER.name()
        )));
        if(teacherList.isEmpty()) {
            throw new UserNotFoundException(userSubjectControlType.teacherId().toString());
        }
    }

    private void checkRightConstraints(UserSubjectControlTypeDto userSubjectControlType) {
        var userSubjectControlTypeList = userSubjectControlTypeCriteriaService.getByCriteria(EntitySpecifications
                .getSpecification(
                    "id",
                        userSubjectControlType.id()
                )
        );
        if(userSubjectControlTypeList.isEmpty()) {
            throw new UserSubjectControlTypeNotFoundException(userSubjectControlType.id().toString());
        }
    }

}
