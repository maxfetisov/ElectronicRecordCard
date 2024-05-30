package diploma.electronicrecordcard.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Builder
public record GroupCreateRequestDto(

        @NotBlank
        @Length(max = 50)
        String name,

        @Length(max = 250)
        String fullName,

        LocalDate admissionDate,

        Short instituteId

) {
}
