package diploma.electronicrecordcard.data.dto;

import lombok.Builder;

@Builder
public record SubjectDto(
        Long id,
        String name
) {
}
