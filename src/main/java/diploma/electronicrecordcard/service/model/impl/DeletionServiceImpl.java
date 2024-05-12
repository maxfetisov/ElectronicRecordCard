package diploma.electronicrecordcard.service.model.impl;

import diploma.electronicrecordcard.data.dto.model.DeletionDto;
import diploma.electronicrecordcard.data.entity.Deletion;
import diploma.electronicrecordcard.data.enumeration.EntityType;
import diploma.electronicrecordcard.repository.model.DeletionRepository;
import diploma.electronicrecordcard.service.criteria.CriteriaService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import diploma.electronicrecordcard.service.model.DeletionService;
import diploma.electronicrecordcard.util.EntitySpecifications;
import diploma.electronicrecordcard.util.VersionUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DeletionServiceImpl implements DeletionService {

    DeletionRepository deletionRepository;

    Mapper<DeletionDto, Deletion> deletionMapper;

    CriteriaService<Deletion> deletionCriteriaService;

    @Override
    public List<DeletionDto> getAll() {
        return deletionRepository.findAll().stream()
                .map(deletionMapper::toDto)
                .toList();
    }

    @Override
    public DeletionDto create(EntityType entityType, Long entityId) {
        return deletionMapper.toDto(deletionRepository.save(Deletion.builder()
                .entityId(entityId)
                .entityType(entityType)
                .version(deletionRepository.getNextVersion())
                .build()));
    }

    @Override
    @Transactional
    public List<DeletionDto> getByCriteria(Map<String, Object> criteria) {
        return deletionCriteriaService.getByCriteria(EntitySpecifications.<Deletion>getSpecification(criteria)
                .orElse(null)).stream()
                .map(deletionMapper::toDto)
                .toList();
    }

    @Override
    public List<DeletionDto> getByCriteria(Map<String, Object> criteria, Long version) {
        var specification = EntitySpecifications.<Deletion>getSpecification(criteria);
        var versionSpecification = VersionUtil.<Deletion>getVersionSpecification(version);
        return deletionCriteriaService.getByCriteria(specification.map((spec)
                -> spec.and(versionSpecification)).orElse(versionSpecification)).stream()
                .map(deletionMapper::toDto)
                .toList();
    }

}
