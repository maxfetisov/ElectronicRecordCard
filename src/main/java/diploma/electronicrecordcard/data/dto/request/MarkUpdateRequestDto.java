package diploma.electronicrecordcard.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record MarkUpdateRequestDto(

        @NotNull
        Short id,

        @NotBlank
        @Length(max = 30)
        String title
) {
}
