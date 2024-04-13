package diploma.electronicrecordcard.data.dto;

import lombok.Builder;

@Builder
public record ControlTypeDto (
        Short id,
        String name,
        String title
) {
}
