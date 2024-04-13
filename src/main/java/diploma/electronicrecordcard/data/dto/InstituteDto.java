package diploma.electronicrecordcard.data.dto;

import lombok.Builder;

@Builder
public record InstituteDto(
        Short id,
        String name,
        String fullName
) {
}
