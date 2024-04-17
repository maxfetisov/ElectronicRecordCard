package diploma.electronicrecordcard.data.dto.request;

import lombok.Builder;

@Builder
public record MarkUpdateRequestDto (Short id, String title) {
}
