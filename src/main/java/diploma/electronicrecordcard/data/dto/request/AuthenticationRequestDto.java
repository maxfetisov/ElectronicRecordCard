package diploma.electronicrecordcard.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AuthenticationRequestDto (

        @NotBlank
        String login,

        @NotBlank
        String password

) {
}
