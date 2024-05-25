package diploma.electronicrecordcard.service.model.impl;

import diploma.electronicrecordcard.data.dto.model.StudentMarkDto;
import diploma.electronicrecordcard.data.dto.request.StudentMarkCreateRequestDto;
import diploma.electronicrecordcard.data.entity.StudentMark;
import diploma.electronicrecordcard.data.entity.UserSubjectControlType;
import diploma.electronicrecordcard.data.enumeration.MarkName;
import diploma.electronicrecordcard.data.enumeration.RoleName;
import diploma.electronicrecordcard.exception.entitynotfound.MarkNotFoundException;
import diploma.electronicrecordcard.exception.entitynotfound.StudentMarkNotFoundException;
import diploma.electronicrecordcard.exception.entitynotfound.UserSubjectControlTypeNotFoundException;
import diploma.electronicrecordcard.exception.versionconflict.StudentMarkVersionConflictException;
import diploma.electronicrecordcard.repository.model.StudentMarkRepository;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.criteria.CriteriaService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import diploma.electronicrecordcard.service.model.DeletionService;
import diploma.electronicrecordcard.service.model.MarkService;
import diploma.electronicrecordcard.service.model.StudentMarkService;
import diploma.electronicrecordcard.util.EntitySpecifications;
import diploma.electronicrecordcard.util.VersionUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static diploma.electronicrecordcard.data.enumeration.EntityType.STUDENT_MARK;
import static java.util.Objects.isNull;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class StudentMarkServiceImpl implements StudentMarkService {

    StudentMarkRepository studentMarkRepository;

    Mapper<StudentMarkDto, StudentMark> studentMarkMapper;

    DeletionService deletionService;

    AuthorityService authorityService;

    CriteriaService<StudentMark> studentMarkCriteriaService;

    CriteriaService<UserSubjectControlType> userSubjectControlTypeCriteriaService;

    MarkService markService;

    @Override
    public List<StudentMarkDto> getAll() {
        return getByCriteria(Map.of());
    }

    @Override
    public Page<StudentMarkDto> getAll(Pageable pageable) {
        return getByCriteria(Map.of(), pageable);
    }

    @Override
    public StudentMarkDto getById(Long id) {
        return getByCriteria(Map.of("id", id)).stream().findFirst()
                .orElseThrow(() -> new StudentMarkNotFoundException(id.toString()));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public StudentMarkDto create(StudentMarkCreateRequestDto studentMarkDto) {
        authorityService.checkRolesAndThrow(List.of(
                RoleName.TEACHER,
                RoleName.DEAN_OFFICE_EMPLOYEE,
                RoleName.ADMINISTRATOR
        ));
        var studentMark = StudentMarkDto.builder()
                .completionDate(studentMarkDto.completionDate())
                .markId(studentMarkDto.markId())
                .userSubjectControlTypeId(studentMarkDto.userSubjectControlTypeId())
                .version(studentMarkRepository.getNextVersion())
                .build();
        checkExistenceConstraint(studentMark);
        return studentMarkMapper.toDto(studentMarkRepository.save(studentMarkMapper.toEntity(studentMark)));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public StudentMarkDto update(StudentMarkDto studentMarkDto) {
        authorityService.checkRolesAndThrow(List.of(
                RoleName.TEACHER,
                RoleName.DEAN_OFFICE_EMPLOYEE,
                RoleName.ADMINISTRATOR
        ));
        StudentMark studentMark = studentMarkRepository.findById(studentMarkDto.id())
                .orElseThrow(() -> new StudentMarkNotFoundException(studentMarkDto.id().toString()));
        VersionUtil.checkVersionAndThrowVersionConflict(studentMark,
                studentMarkDto,
                StudentMarkVersionConflictException.class);
        checkRightConstraints(studentMarkMapper.toDto(studentMark));
        checkConstraints(studentMarkDto);
        StudentMark newStudentMark = studentMarkMapper.toEntity(studentMarkDto);
        newStudentMark.setVersion(studentMarkRepository.getNextVersion());
        return studentMarkMapper.toDto(studentMarkRepository.save(newStudentMark));
    }

    @Override
    @Transactional
    public List<StudentMarkDto> createOrUpdate(List<StudentMarkDto> studentMarks) {
        authorityService.checkRolesAndThrow(List.of(
                RoleName.TEACHER,
                RoleName.DEAN_OFFICE_EMPLOYEE,
                RoleName.ADMINISTRATOR
        ));
        Map<Boolean, List<StudentMarkDto>> createOrUpdateStudentMarks = studentMarks.stream()
                .collect(Collectors.groupingBy(studentMark -> isNull(studentMark.id())));
        List<StudentMarkDto> result = new ArrayList<>(
                Optional.ofNullable(createOrUpdateStudentMarks.get(true))
                        .map(createRequest -> createRequest.stream()
                        .map(studentMark -> StudentMarkCreateRequestDto.builder()
                                .userSubjectControlTypeId(studentMark.userSubjectControlTypeId())
                                .completionDate(studentMark.completionDate())
                                .markId(studentMark.markId())
                                .build())
                        .map(this::create)
                        .toList()).orElse(List.of())
        );
        result.addAll(Optional.ofNullable(createOrUpdateStudentMarks.get(false))
                .map(updateRequest -> updateRequest.stream()
                .map(this::update)
                .toList()).orElse(List.of())
        );
        return result;
    }

    @Override
    @Transactional
    public void delete(Long id, Long version) {
        authorityService.checkRolesAndThrow(List.of(
                RoleName.TEACHER,
                RoleName.DEAN_OFFICE_EMPLOYEE,
                RoleName.ADMINISTRATOR
        ));
        StudentMark studentMark = studentMarkRepository.findById(id)
                .orElseThrow(() -> new StudentMarkNotFoundException(id.toString()));
        VersionUtil.checkVersionAndThrowVersionConflict(studentMark,
                () -> version,
                StudentMarkVersionConflictException.class);
        checkRightConstraints(studentMarkMapper.toDto(studentMark));
        studentMarkRepository.deleteById(id);
        deletionService.create(STUDENT_MARK, id);
    }

    @Override
    public List<StudentMarkDto> getByCriteria(Map<String, Object> criteria) {
        return studentMarkCriteriaService.getByCriteria(EntitySpecifications.<StudentMark>getSpecification(criteria)
                        .orElse(null)).stream()
                .map(studentMarkMapper::toDto)
                .toList();
    }

    @Override
    public List<StudentMarkDto> getByCriteria(Map<String, Object> criteria, Long version) {
        var specification = EntitySpecifications.<StudentMark>getSpecification(criteria);
        var versionSpecification = VersionUtil.<StudentMark>getVersionSpecification(version);
        return studentMarkCriteriaService.getByCriteria(specification.map((spec) -> spec.and(versionSpecification))
                        .orElse(versionSpecification)).stream()
                .map(studentMarkMapper::toDto)
                .toList();
    }

    @Override
    public Page<StudentMarkDto> getByCriteria(Map<String, Object> criteria, Pageable pageable) {
        return studentMarkCriteriaService.getByCriteria(EntitySpecifications.<StudentMark>getSpecification(criteria)
                        .orElse(null), pageable)
                .map(studentMarkMapper::toDto);
    }

    @Override
    public Page<StudentMarkDto> getByCriteria(Map<String, Object> criteria, Long version, Pageable pageable) {
        var specification = EntitySpecifications.<StudentMark>getSpecification(criteria);
        var versionSpecification = VersionUtil.<StudentMark>getVersionSpecification(version);
        return studentMarkCriteriaService.getByCriteria(specification.map((spec) -> spec.and(versionSpecification))
                        .orElse(versionSpecification), pageable)
                .map(studentMarkMapper::toDto);
    }

    private void checkConstraints(StudentMarkDto studentMark) {
        checkExistenceConstraint(studentMark);
        checkRightConstraints(studentMark);
    }

    private void checkExistenceConstraint(StudentMarkDto studentMark) {
        var mark = markService.getById(studentMark.markId());
        if (authorityService.hasAnyAuthority(List.of(RoleName.TEACHER))
                && Objects.equals(mark.name(), MarkName.NON_ADMISSION.name())) {
            throw new MarkNotFoundException(studentMark.markId().toString());
        }
        var userSubjectControlTypeList = userSubjectControlTypeCriteriaService.getByCriteria(EntitySpecifications
                .getSpecification(
                   "id",
                   studentMark.userSubjectControlTypeId()
                ));
        if(userSubjectControlTypeList.isEmpty()) {
            throw new UserSubjectControlTypeNotFoundException(studentMark.userSubjectControlTypeId().toString());
        }
    }

    private void checkRightConstraints(StudentMarkDto studentMark) {
        var studentMarkList = studentMarkCriteriaService.getByCriteria(EntitySpecifications
                .getSpecification(
                        "id",
                        studentMark.id()
                )
        );
        if(studentMarkList.isEmpty()) {
            throw new StudentMarkNotFoundException(studentMark.id().toString());
        }
    }

}
