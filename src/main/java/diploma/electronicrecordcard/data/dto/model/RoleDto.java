package diploma.electronicrecordcard.data.dto.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record RoleDto(

        @NotNull
        Short id,

        @NotBlank
        @Length(max = 20)
        String name

) {
}
