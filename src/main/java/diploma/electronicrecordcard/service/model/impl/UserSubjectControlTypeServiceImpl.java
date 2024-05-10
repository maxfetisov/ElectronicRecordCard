package diploma.electronicrecordcard.service.model.impl;

import diploma.electronicrecordcard.data.dto.model.UserSubjectControlTypeDto;
import diploma.electronicrecordcard.data.dto.request.UserSubjectControlTypeCreateRequestDto;
import diploma.electronicrecordcard.data.entity.UserSubjectControlType;
import diploma.electronicrecordcard.exception.entitynotfound.UserSubjectControlTypeNotFoundException;
import diploma.electronicrecordcard.exception.versionconflict.UserSubjectControlTypeVersionConflictException;
import diploma.electronicrecordcard.repository.model.UserSubjectControlTypeRepository;
import diploma.electronicrecordcard.service.model.DeletionService;
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

    @Override
    public List<UserSubjectControlTypeDto> getAll() {
        return userSubjectControlTypeRepository.findAll().stream()
                .map(userSubjectControlTypeMapper::toDto)
                .toList();
    }

    @Override
    public List<UserSubjectControlTypeDto> getByCriteria(UserSubjectControlTypeDto criteria) {
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
    public List<UserSubjectControlTypeDto> getByCriteria(Map<String, Object> criteria, Long version) {
        var specification = EntitySpecifications.<UserSubjectControlType>getSpecification(criteria);
        var versionSpecification = VersionUtil.<UserSubjectControlType>getVersionSpecification(version);
        return getByCriteria(Optional.of(specification.map((spec) -> spec.and(versionSpecification))
                .orElse(versionSpecification)));
    }

    @Override
    public List<UserSubjectControlTypeDto> getByCriteria(Map<String, Object> criteria) {
        return getByCriteria(EntitySpecifications.getSpecification(criteria));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserSubjectControlTypeDto create(UserSubjectControlTypeCreateRequestDto userSubjectControlTypeDto) {
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
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserSubjectControlTypeDto update(UserSubjectControlTypeDto userSubjectControlTypeDto) {
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
        UserSubjectControlType userSubjectControlType = userSubjectControlTypeRepository.findById(id)
                .orElseThrow(() -> new UserSubjectControlTypeNotFoundException(id.toString()));
        VersionUtil.checkVersionAndThrowVersionConflict(userSubjectControlType,
                () -> version,
                UserSubjectControlTypeVersionConflictException.class);
        userSubjectControlTypeRepository.deleteById(id);
        deletionService.create(USER_SUBJECT_CONTROL_TYPE, id);

    }

    private List<UserSubjectControlTypeDto> getByCriteria(
            Optional<Specification<UserSubjectControlType>> specification
    ) {
        return specification.map(userSubjectControlTypeRepository::findAll)
                .orElse(userSubjectControlTypeRepository.findAll())
                .stream()
                .map(userSubjectControlTypeMapper::toDto)
                .toList();
    }
}
