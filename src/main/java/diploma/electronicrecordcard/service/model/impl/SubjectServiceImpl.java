package diploma.electronicrecordcard.service.model.impl;

import diploma.electronicrecordcard.data.dto.model.SubjectDto;
import diploma.electronicrecordcard.data.dto.request.SubjectCreateRequestDto;
import diploma.electronicrecordcard.data.entity.Subject;
import diploma.electronicrecordcard.data.enumeration.RoleName;
import diploma.electronicrecordcard.exception.entitynotfound.SubjectNotFoundException;
import diploma.electronicrecordcard.exception.versionconflict.SubjectVersionConflictException;
import diploma.electronicrecordcard.repository.model.SubjectRepository;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.criteria.CriteriaService;
import diploma.electronicrecordcard.service.model.DeletionService;
import diploma.electronicrecordcard.service.model.SubjectService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import diploma.electronicrecordcard.util.EntitySpecifications;
import diploma.electronicrecordcard.util.VersionUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static diploma.electronicrecordcard.data.enumeration.EntityType.SUBJECT;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    SubjectRepository subjectRepository;

    Mapper<SubjectDto, Subject> subjectMapper;

    DeletionService deletionService;

    AuthorityService authorityService;

    CriteriaService<Subject> subjectCriteriaService;

    @Override
    public List<SubjectDto> getAll() {
        return getByCriteria(Map.of());
    }

    @Override
    public SubjectDto getById(Long id) {
        return getByCriteria(Map.of("id", id)).stream().findFirst()
                .orElseThrow(() -> new SubjectNotFoundException(id.toString()));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public SubjectDto create(SubjectCreateRequestDto subjectDto) {
        authorityService.checkRolesAndThrow(List.of(RoleName.DEAN_OFFICE_EMPLOYEE, RoleName.ADMINISTRATOR));
        return subjectMapper.toDto(subjectRepository.save(subjectMapper.toEntity(SubjectDto.builder()
                .name(subjectDto.name())
                .deleted(false)
                .version(subjectRepository.getNextVersion())
                .build())));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public SubjectDto update(SubjectDto subjectDto) {
        authorityService.checkRolesAndThrow(List.of(RoleName.DEAN_OFFICE_EMPLOYEE, RoleName.ADMINISTRATOR));
        Subject subject = subjectRepository.findById(subjectDto.id())
                .orElseThrow(() -> new SubjectNotFoundException(subjectDto.id().toString()));
        VersionUtil.checkVersionAndThrowVersionConflict(subject, subjectDto, SubjectVersionConflictException.class);
        Subject newSubject = subjectMapper.toEntity(subjectDto);
        newSubject.setVersion(subjectRepository.getNextVersion());
        return subjectMapper.toDto(subjectRepository.save(newSubject));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public SubjectDto delete(Long id, Long version) {
        authorityService.checkRolesAndThrow(List.of(RoleName.DEAN_OFFICE_EMPLOYEE, RoleName.ADMINISTRATOR));
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException(id.toString()));
        VersionUtil.checkVersionAndThrowVersionConflict(subject, () -> version, SubjectVersionConflictException.class);
        subject.setDeleted(true);
        subject.setVersion(subjectRepository.getNextVersion());
        deletionService.create(SUBJECT, id);
        return subjectMapper.toDto(subjectRepository.save(subject));

    }

    @Override
    public List<SubjectDto> getByCriteria(Map<String, Object> criteria) {
        return subjectCriteriaService.getByCriteria(EntitySpecifications.<Subject>getSpecification(criteria)
                .orElse(null)).stream()
                .map(subjectMapper::toDto)
                .toList();
    }

    @Override
    public List<SubjectDto> getByCriteria(Map<String, Object> criteria, Long version) {
        var specification = EntitySpecifications.<Subject>getSpecification(criteria);
        var versionSpecification = VersionUtil.<Subject>getVersionSpecification(version);
        return subjectCriteriaService.getByCriteria(specification.map((spec)
                -> spec.and(versionSpecification)).orElse(versionSpecification)).stream()
                .map(subjectMapper::toDto)
                .toList();
    }
}
