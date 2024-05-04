package diploma.electronicrecordcard.service.model.impl;

import diploma.electronicrecordcard.data.dto.model.UserSubjectControlTypeDto;
import diploma.electronicrecordcard.data.dto.request.UserSubjectControlTypeCreateRequestDto;
import diploma.electronicrecordcard.data.entity.UserSubjectControlType;
import diploma.electronicrecordcard.exception.entitynotfound.UserSubjectControlTypeNotFoundException;
import diploma.electronicrecordcard.exception.versionconflict.UserSubjectControlTypeVersionConflictException;
import diploma.electronicrecordcard.repository.model.UserSubjectControlTypeRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserSubjectControlTypeServiceImpl implements UserSubjectControlTypeService {

    UserSubjectControlTypeRepository userSubjectControlTypeRepository;

    Mapper<UserSubjectControlTypeDto, UserSubjectControlType> userSubjectControlTypeMapper;

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
    public List<UserSubjectControlTypeDto> getByCriteria(Map<String, Object> criteria) {
        var specification = EntitySpecifications.getSpecification(criteria, UserSubjectControlType.class);
        return specification.map(userSubjectControlTypeRepository::findAll)
                .orElse(userSubjectControlTypeRepository.findAll())
                .stream()
                .map(userSubjectControlTypeMapper::toDto)
                .toList();
    }

    @Override
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
    public void delete(Long id) {
        if(!userSubjectControlTypeRepository.existsById(id)) {
            throw new UserSubjectControlTypeNotFoundException(id.toString());
        }
        userSubjectControlTypeRepository.deleteById(id);
    }
}
