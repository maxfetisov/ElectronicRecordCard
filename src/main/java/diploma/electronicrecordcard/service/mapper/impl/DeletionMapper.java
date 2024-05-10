package diploma.electronicrecordcard.service.mapper.impl;

import diploma.electronicrecordcard.data.dto.model.DeletionDto;
import diploma.electronicrecordcard.data.entity.Deletion;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class DeletionMapper implements Mapper<DeletionDto, Deletion> {

    @Override
    public DeletionDto toDto(Deletion deletion) {
        return DeletionDto.builder()
                .id(deletion.getId())
                .entityType(deletion.getEntityType())
                .entityId(deletion.getEntityId())
                .version(deletion.getVersion())
                .build();
    }

    @Override
    public Deletion toEntity(DeletionDto deletionDto) {
        return Deletion.builder()
                .id(deletionDto.id())
                .entityType(deletionDto.entityType())
                .entityId(deletionDto.entityId())
                .version(deletionDto.getVersion())
                .build();
    }

}
