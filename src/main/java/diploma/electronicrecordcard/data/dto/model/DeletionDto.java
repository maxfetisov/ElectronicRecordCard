package diploma.electronicrecordcard.data.dto.model;

import diploma.electronicrecordcard.data.Versionable;
import diploma.electronicrecordcard.data.enumeration.EntityType;
import lombok.Builder;
import lombok.Getter;

@Builder
public record DeletionDto(

        Long id,

        EntityType entityType,

        Long entityId,

        @Getter
        Long version

) implements Versionable {
}
