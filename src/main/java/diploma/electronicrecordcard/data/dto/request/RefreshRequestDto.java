package diploma.electronicrecordcard.data.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record RefreshRequestDto(

        @NotEmpty
        String refreshToken
) {
}
