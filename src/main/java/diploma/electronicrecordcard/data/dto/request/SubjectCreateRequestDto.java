package diploma.electronicrecordcard.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record SubjectCreateRequestDto(

        @NotBlank
        @Length(max = 50)
        String name

) {
}
