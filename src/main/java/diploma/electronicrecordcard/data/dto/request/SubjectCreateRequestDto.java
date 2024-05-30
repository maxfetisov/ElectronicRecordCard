package diploma.electronicrecordcard.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record SubjectCreateRequestDto(

        @NotBlank
        @Length(max = 250)
        String name

) {
}
