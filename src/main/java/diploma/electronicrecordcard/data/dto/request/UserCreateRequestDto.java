package diploma.electronicrecordcard.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Builder
public record UserCreateRequestDto (

        @NotBlank
        @Length(max = 20)
        String login,

        @NotBlank
        @Length(min = 12)
        String password,

        @NotBlank
        @Length(max = 50)
        String lastName,

        @NotBlank
        @Length(max = 50)
        String firstName,

        @Length(max = 50)
        String middleName,

        @Length(max = 20)
        String phoneNumber,

        @Length(max = 100)
        @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
        String email,

        @Length(max = 20)
        String recordBookNumber,

        Integer groupId,

        Short instituteId,

        List<Short> roles

) {
}
