package diploma.electronicrecordcard.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record ChangePasswordRequestDto (

        @NotBlank
        @Length(min = 12)
        String oldPassword,

        @NotBlank
        @Length(min = 12)
        String newPassword

) {
}
