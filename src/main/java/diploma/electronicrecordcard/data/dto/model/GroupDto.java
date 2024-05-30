package diploma.electronicrecordcard.data.dto.model;

import diploma.electronicrecordcard.data.Versionable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Builder
public record GroupDto(

        @NotNull
        Integer id,

        @NotBlank
        @Length(max = 50)
        String name,

        @NotBlank
        @Length(max = 250)
        String fullName,

        LocalDate admissionDate,

        Boolean deleted,

        Short instituteId,

        @NotNull
        @Getter
        Long version

) implements Versionable {
}
