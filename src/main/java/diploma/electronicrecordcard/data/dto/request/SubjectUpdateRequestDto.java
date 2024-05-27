package diploma.electronicrecordcard.data.dto.request;

import diploma.electronicrecordcard.data.Versionable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Builder
public record SubjectUpdateRequestDto(

        @NotNull
        Long id,

        @NotBlank
        @Length(max = 50)
        String name,

        @NotNull
        @Getter
        Long version

) implements Versionable {
}
