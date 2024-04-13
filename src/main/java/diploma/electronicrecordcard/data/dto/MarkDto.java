package diploma.electronicrecordcard.data.dto;

import lombok.Builder;

@Builder
public record MarkDto(
        Short id,
        String name,
        String title,
        Short value
) {
}
