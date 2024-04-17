package diploma.electronicrecordcard.data.dto.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Builder
public record GroupDto(

        @NotNull
        Integer id,

        @NotBlank
        @Length(max = 10)
        String name,

        @NotBlank
        @Length(max = 50)
        String fullName,

        LocalDate admissionDate,

        Boolean deleted,

        @NotNull
        Short instituteId
) {
}
