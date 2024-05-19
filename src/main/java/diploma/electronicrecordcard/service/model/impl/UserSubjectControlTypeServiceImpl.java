package diploma.electronicrecordcard.service.model.impl;

import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.dto.model.UserSubjectControlTypeDto;
import diploma.electronicrecordcard.data.dto.request.UserSubjectControlTypeCreateByGroupRequest;
import diploma.electronicrecordcard.data.dto.request.UserSubjectControlTypeCreateRequestDto;
import diploma.electronicrecordcard.data.entity.UserSubjectControlType;
import diploma.electronicrecordcard.data.enumeration.RoleName;
import diploma.electronicrecordcard.exception.entitynotfound.UserSubjectControlTypeNotFoundException;
import diploma.electronicrecordcard.exception.versionconflict.UserSubjectControlTypeVersionConflictException;
import diploma.electronicrecordcard.repository.model.UserSubjectControlTypeRepository;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.criteria.CriteriaService;
import diploma.electronicrecordcard.service.model.DeletionService;
import diploma.electronicrecordcard.service.model.UserService;
import diploma.electronicrecordcard.service.model.UserSubjectControlTypeService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import diploma.electronicrecordcard.util.EntitySpecifications;
import diploma.electronicrecordcard.util.UserSubjectControlTypeSpecifications;
import diploma.electronicrecordcard.util.VersionUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static diploma.electronicrecordcard.data.enumeration.EntityType.USER_SUBJECT_CONTROL_TYPE;
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

    @Override
    public List<UserSubjectControlTypeDto> getAll() {
        return getByCriteria(Map.of());
    }

    @Override
    public List<UserSubjectControlTypeDto> getByCriteria(UserSubjectControlTypeDto criteria) {
        authorityService.checkRolesAndThrow(List.of(RoleName.ADMINISTRATOR));
        List<Specification<UserSubjectControlType>> specs = new ArrayList<>();

        if (nonNull(criteria.id())) {
            specs.add(UserSubjectControlTypeSpecifications.getIdSpecification(criteria.id()));
        }
        if (nonNull(criteria.semester())) {
            specs.add(UserSubjectControlTypeSpecifications.getSemesterSpecification(criteria.semester()));
        }
        if (nonNull(criteria.hoursNumber())) {
            specs.add(UserSubjectControlTypeSpecifications.getHoursNumberSpecification(criteria.hoursNumber()));
        }
        if (nonNull(criteria.studentId())) {
            specs.add(UserSubjectControlTypeSpecifications.getStudentIdSpecification(criteria.studentId()));
        }
        if (nonNull(criteria.teacherId())) {
            specs.add(UserSubjectControlTypeSpecifications.getTeacherIdSpecification(criteria.teacherId()));
        }
        if (nonNull(criteria.subjectId())) {
            specs.add(UserSubjectControlTypeSpecifications.getSubjectIdSpecification(criteria.subjectId()));
        }
        if (nonNull(criteria.controlTypeId())) {
            specs.add(UserSubjectControlTypeSpecifications.getControlTypeIdSpecification(criteria.controlTypeId()));
        }

        return specs.stream().reduce(Specification::and).map(userSubjectControlTypeRepository::findAll)
                .orElse(userSubjectControlTypeRepository.findAll())
                .stream()
                .map(userSubjectControlTypeMapper::toDto)
                .toList();

    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserSubjectControlTypeDto create(UserSubjectControlTypeCreateRequestDto userSubjectControlTypeDto) {
        authorityService.checkRolesAndThrow(List.of(RoleName.DEAN_OFFICE_EMPLOYEE, RoleName.ADMINISTRATOR));
        return userSubjectControlTypeMapper.toDto(userSubjectControlTypeRepository.save(
                userSubjectControlTypeMapper.toEntity(
                        UserSubjectControlTypeDto.builder()
                                .teacherId(userSubjectControlTypeDto.teacherId())
                                .studentId(userSubjectControlTypeDto.studentId())
                                .subjectId(userSubjectControlTypeDto.subjectId())
                                .controlTypeId(userSubjectControlTypeDto.controlTypeId())
                                .semester(userSubjectControlTypeDto.semester())
                                .hoursNumber(userSubjectControlTypeDto.hoursNumber())
                                .version(userSubjectControlTypeRepository.getNextVersion())
                                .build()
                )));
    }

    @Override
    public List<UserSubjectControlTypeDto> create(UserSubjectControlTypeCreateByGroupRequest userSubjectControlTypeDto) {
        authorityService.checkRolesAndThrow(List.of(RoleName.DEAN_OFFICE_EMPLOYEE, RoleName.ADMINISTRATOR));
        List<UserDto> users = userService.getByCriteria(Map.of("group.id", userSubjectControlTypeDto.groupId()));
        List<UserSubjectControlTypeDto> userSubjectControlTypes = new ArrayList<>();
        for (var user : users) {
            userSubjectControlTypes.add(UserSubjectControlTypeDto.builder()
                    .subjectId(userSubjectControlTypeDto.subjectId())
                    .teacherId(userSubjectControlTypeDto.teacherId())
                    .controlTypeId(userSubjectControlTypeDto.controlTypeId())
                    .hoursNumber(userSubjectControlTypeDto.hoursNumber())
                    .semester(userSubjectControlTypeDto.semester())
                    .studentId(user.id())
                    .build());
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

}
