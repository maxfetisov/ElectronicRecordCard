package diploma.electronicrecordcard.data.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import diploma.electronicrecordcard.data.Versionable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Builder
public record UserDto (

        @NotNull
        Long id,

        @NotBlank
        @Length(max = 50)
        String login,

        @JsonIgnore
        String password,

        @NotBlank
        @Length(max = 100)
        String lastName,

        @NotBlank
        @Length(max = 100)
        String firstName,

        @Length(max = 100)
        String middleName,

        @Length(max = 20)
        String phoneNumber,

        @Length(max = 100)
        @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
        String email,

        @Length(max = 20)
        String recordBookNumber,

        Boolean deleted,

        Integer groupId,

        @NotNull
        Short instituteId,

        List<Short> roles,

        @NotNull
        @Getter
        Long version
) implements Versionable {
}
