package diploma.electronicrecordcard.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Builder
public record GroupUpdateRequestDto(

        @NotNull
        Integer id,

        @NotBlank
        @Length(max = 10)
        String name,

        @Length(max = 50)
        String fullName,

        LocalDate admissionDate,

        @NotNull
        Short instituteId
) {
}
