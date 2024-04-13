package diploma.electronicrecordcard.data.dto.model;

import lombok.Builder;

@Builder
public record InstituteDto(
        Short id,
        String name,
        String fullName
) {
}
