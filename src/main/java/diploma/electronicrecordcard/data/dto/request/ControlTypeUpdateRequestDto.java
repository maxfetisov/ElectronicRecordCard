package diploma.electronicrecordcard.data.dto.request;

import lombok.Builder;

@Builder
public record ControlTypeUpdateRequestDto(Short id, String title) {
}
