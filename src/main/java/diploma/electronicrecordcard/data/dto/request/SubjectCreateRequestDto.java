package diploma.electronicrecordcard.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record SubjectCreateRequestDto(

        @NotBlank
        @Length(max = 50)
        String name

) {
}
