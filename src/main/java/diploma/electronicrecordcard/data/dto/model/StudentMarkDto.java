package diploma.electronicrecordcard.data.dto.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record StudentMarkDto(

        @NotNull
        Long id,

        @NotNull
        LocalDate completionDate,

        @NotNull
        Short markId,

        @NotNull
        Long userSubjectControlTypeId
) {
}
