package diploma.electronicrecordcard.data.dto.model;

import diploma.electronicrecordcard.data.Versionable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Builder
public record SubjectDto(

        @NotNull
        Long id,

        @NotBlank
        @Length(max = 250)
        String name,

        Boolean deleted,

        @NotNull
        @Getter
        Long version

) implements Versionable {
}
