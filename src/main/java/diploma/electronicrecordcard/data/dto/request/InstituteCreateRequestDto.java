package diploma.electronicrecordcard.data.dto.request;

import lombok.Builder;

@Builder
public record InstituteCreateRequestDto(String name, String fullName) {
}
