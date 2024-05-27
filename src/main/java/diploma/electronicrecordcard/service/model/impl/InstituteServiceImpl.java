package diploma.electronicrecordcard.service.model.impl;

import diploma.electronicrecordcard.data.dto.model.InstituteDto;
import diploma.electronicrecordcard.data.dto.request.InstituteCreateRequestDto;
import diploma.electronicrecordcard.data.dto.request.InstituteUpdateRequestDto;
import diploma.electronicrecordcard.data.entity.Institute;
import diploma.electronicrecordcard.data.enumeration.RoleName;
import diploma.electronicrecordcard.exception.entitynotfound.InstituteNotFoundException;
import diploma.electronicrecordcard.exception.versionconflict.InstituteVersionConflictException;
import diploma.electronicrecordcard.repository.model.InstituteRepository;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.criteria.CriteriaService;
import diploma.electronicrecordcard.service.model.DeletionService;
import diploma.electronicrecordcard.service.model.InstituteService;
import diploma.electronicrecordcard.service.mapper.Mapper;
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

import java.util.List;
import java.util.Map;

import static diploma.electronicrecordcard.data.enumeration.EntityType.INSTITUTE;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class InstituteServiceImpl implements InstituteService {

    InstituteRepository instituteRepository;

    Mapper<InstituteDto, Institute> instituteMapper;

    DeletionService deletionService;

    AuthorityService authorityService;

    CriteriaService<Institute> instituteCriteriaService;

    @Override
    public List<InstituteDto> getAll() {
        return getByCriteria(Map.of());
    }

    @Override
    public Page<InstituteDto> getAll(Pageable pageable) {
        return getByCriteria(Map.of(), pageable);
    }

    @Override
    public InstituteDto getById(Short id) {
        return getByCriteria(Map.of("id", id)).stream().findFirst()
                .orElseThrow(() -> new InstituteNotFoundException(id.toString()));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public InstituteDto create(InstituteCreateRequestDto instituteDto) {
        authorityService.checkRolesAndThrow(List.of(RoleName.ADMINISTRATOR));
        return instituteMapper.toDto(instituteRepository.save(instituteMapper.toEntity(InstituteDto.builder()
                .name(instituteDto.name())
                .fullName(instituteDto.fullName())
                .version(instituteRepository.getNextVersion())
                .build())));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public InstituteDto update(InstituteUpdateRequestDto instituteDto) {
        authorityService.checkRolesAndThrow(List.of(RoleName.ADMINISTRATOR));
        Institute institute = instituteRepository.findById(instituteDto.id())
                .orElseThrow(() -> new InstituteNotFoundException(instituteDto.id().toString()));
        VersionUtil.checkVersionAndThrowVersionConflict(institute,
                instituteDto,
                InstituteVersionConflictException.class);
        institute.setName(instituteDto.name());
        institute.setFullName(instituteDto.fullName());
        institute.setVersion(instituteRepository.getNextVersion());
        return instituteMapper.toDto(instituteRepository.save(institute));
    }

    @Override
    @Transactional
    public InstituteDto delete(Short id, Long version) {
        authorityService.checkRolesAndThrow(List.of(RoleName.ADMINISTRATOR));
        Institute institute = instituteRepository.findById(id)
                .orElseThrow(() -> new InstituteNotFoundException(id.toString()));
        VersionUtil.checkVersionAndThrowVersionConflict(institute,
                () -> version,
                InstituteVersionConflictException.class);
        institute.setDeleted(true);
        deletionService.create(INSTITUTE, id.longValue());
        return instituteMapper.toDto(instituteRepository.save(institute));
    }

    @Override
    public List<InstituteDto> getByCriteria(Map<String, Object> criteria) {
        return instituteCriteriaService.getByCriteria(EntitySpecifications.<Institute>getSpecification(criteria)
                .orElse(null)).stream()
                .map(instituteMapper::toDto)
                .toList();
    }

    @Override
    public List<InstituteDto> getByCriteria(Map<String, Object> criteria, Long version) {
        var specification = EntitySpecifications.<Institute>getSpecification(criteria);
        var versionSpecification = VersionUtil.<Institute>getVersionSpecification(version);
        return instituteCriteriaService.getByCriteria(specification.map((spec)
                        -> spec.and(versionSpecification)).orElse(versionSpecification)).stream()
                .map(instituteMapper::toDto)
                .toList();
    }

    @Override
    public Page<InstituteDto> getByCriteria(Map<String, Object> criteria, Pageable pageable) {
        return instituteCriteriaService.getByCriteria(EntitySpecifications.<Institute>getSpecification(criteria)
                        .orElse(null), pageable)
                .map(instituteMapper::toDto);
    }

    @Override
    public Page<InstituteDto> getByCriteria(Map<String, Object> criteria, Long version, Pageable pageable) {
        var specification = EntitySpecifications.<Institute>getSpecification(criteria);
        var versionSpecification = VersionUtil.<Institute>getVersionSpecification(version);
        return instituteCriteriaService.getByCriteria(specification.map((spec)
                        -> spec.and(versionSpecification)).orElse(versionSpecification), pageable)
                .map(instituteMapper::toDto);
    }

}
