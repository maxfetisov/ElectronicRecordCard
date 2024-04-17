package diploma.electronicrecordcard.data.dto.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record InstituteDto(

        @NotNull
        Short id,

        @NotBlank
        @Length(max = 10)
        String name,

        @Length(max = 50)
        String fullName
) {
}
