package diploma.electronicrecordcard.data.dto.model;

import lombok.Builder;

@Builder
public record RoleDto(
        Short id,
        String name
) {
}
