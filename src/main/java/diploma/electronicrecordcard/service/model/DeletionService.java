package diploma.electronicrecordcard.service.model;

import diploma.electronicrecordcard.data.dto.model.DeletionDto;
import diploma.electronicrecordcard.data.enumeration.EntityType;
import diploma.electronicrecordcard.service.criteria.CriteriaService;

import java.util.List;

public interface DeletionService extends CriteriaService<DeletionDto> {

    List<DeletionDto> getAll();

    DeletionDto create(EntityType entityType, Long entityId);

}
