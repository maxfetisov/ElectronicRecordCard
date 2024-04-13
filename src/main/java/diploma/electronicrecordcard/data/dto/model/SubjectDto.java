package diploma.electronicrecordcard.data.dto.model;

import lombok.Builder;

@Builder
public record SubjectDto(
        Long id,
        String name
) {
}
