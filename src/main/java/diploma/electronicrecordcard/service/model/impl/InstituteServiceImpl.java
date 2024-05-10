package diploma.electronicrecordcard.service.model.impl;

import diploma.electronicrecordcard.data.dto.model.InstituteDto;
import diploma.electronicrecordcard.data.dto.request.InstituteCreateRequestDto;
import diploma.electronicrecordcard.data.entity.Institute;
import diploma.electronicrecordcard.exception.entitynotfound.InstituteNotFoundException;
import diploma.electronicrecordcard.exception.versionconflict.InstituteVersionConflictException;
import diploma.electronicrecordcard.repository.model.InstituteRepository;
import diploma.electronicrecordcard.service.model.DeletionService;
import diploma.electronicrecordcard.service.model.InstituteService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import diploma.electronicrecordcard.util.EntitySpecifications;
import diploma.electronicrecordcard.util.VersionUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static diploma.electronicrecordcard.data.enumeration.EntityType.INSTITUTE;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class InstituteServiceImpl implements InstituteService {

    InstituteRepository instituteRepository;

    Mapper<InstituteDto, Institute> instituteMapper;

    DeletionService deletionService;

    @Override
    public List<InstituteDto> findAll() {
        return instituteRepository.findAll().stream()
                .map(instituteMapper::toDto)
                .toList();
    }

    @Override
    public InstituteDto findById(Short id) {
        return instituteMapper.toDto(instituteRepository.findById(id)
                .orElseThrow(() -> new InstituteNotFoundException(id.toString())));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public InstituteDto create(InstituteCreateRequestDto instituteDto) {
        return instituteMapper.toDto(instituteRepository.save(instituteMapper.toEntity(InstituteDto.builder()
                .name(instituteDto.name())
                .fullName(instituteDto.fullName())
                .version(instituteRepository.getNextVersion())
                .build())));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public InstituteDto update(InstituteDto instituteDto) {
        Institute institute = instituteRepository.findById(instituteDto.id())
                .orElseThrow(() -> new InstituteNotFoundException(instituteDto.id().toString()));
        VersionUtil.checkVersionAndThrowVersionConflict(institute,
                instituteDto,
                InstituteVersionConflictException.class);
        Institute newInstitute = instituteMapper.toEntity(instituteDto);
        newInstitute.setVersion(instituteRepository.getNextVersion());
        return instituteMapper.toDto(instituteRepository.save(newInstitute));
    }

    @Override
    @Transactional
    public void delete(Short id, Long version) {
        Institute institute = instituteRepository.findById(id)
                .orElseThrow(() -> new InstituteNotFoundException(id.toString()));
        VersionUtil.checkVersionAndThrowVersionConflict(institute,
                () -> version,
                InstituteVersionConflictException.class);
        instituteRepository.deleteById(id);
        deletionService.create(INSTITUTE, id.longValue());
    }

    @Override
    public List<InstituteDto> getByCriteria(Map<String, Object> criteria) {
        return getByCriteria(EntitySpecifications.getSpecification(criteria));
    }

    @Override
    public List<InstituteDto> getByCriteria(Map<String, Object> criteria, Long version) {
        var specification = EntitySpecifications.<Institute>getSpecification(criteria);
        var versionSpecification = VersionUtil.<Institute>getVersionSpecification(version);
        return getByCriteria(Optional.of(specification.map((spec) -> spec.and(versionSpecification))
                .orElse(versionSpecification)));
    }

    private List<InstituteDto> getByCriteria(Optional<Specification<Institute>> specification) {
        return specification.map(instituteRepository::findAll)
                .orElse(instituteRepository.findAll())
                .stream()
                .map(instituteMapper::toDto)
                .toList();
    }
}
