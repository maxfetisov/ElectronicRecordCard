package diploma.electronicrecordcard.data.dto.model;

import diploma.electronicrecordcard.data.Versionable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Builder
public record InstituteDto(

        @NotNull
        Short id,

        @NotBlank
        @Length(max = 50)
        String name,

        @Length(max = 250)
        String fullName,

        Boolean deleted,

        @NotNull
        @Getter
        Long version

) implements Versionable {
}
