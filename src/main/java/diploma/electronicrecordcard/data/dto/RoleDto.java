package diploma.electronicrecordcard.data.dto;

import lombok.Builder;

@Builder
public record RoleDto(
        Short id,
        String name
) {
}
