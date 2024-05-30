package diploma.electronicrecordcard.data.dto.model;

import diploma.electronicrecordcard.data.Versionable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Builder
public record MarkDto(

        @NotNull
        Short id,

        @NotBlank
        @Length(max = 100)
        String name,

        @NotBlank
        @Length(max = 100)
        String title,

        @NotNull
        Short value,

        @NotNull
        @Getter
        Long version

) implements Versionable {
}
