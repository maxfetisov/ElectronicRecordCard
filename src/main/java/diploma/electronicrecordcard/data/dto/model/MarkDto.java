package diploma.electronicrecordcard.data.dto.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record MarkDto(

        @NotNull
        Short id,

        @NotBlank
        @Length(max = 15)
        String name,

        @NotBlank
        @Length(max = 30)
        String title,

        @NotNull
        Short value
) {
}
