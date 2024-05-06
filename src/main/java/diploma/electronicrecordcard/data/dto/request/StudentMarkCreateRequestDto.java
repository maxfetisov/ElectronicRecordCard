package diploma.electronicrecordcard.data.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record StudentMarkCreateRequestDto (

        @NotNull
        LocalDate completionDate,

        @NotNull
        Short markId,

        @NotNull
        Long userSubjectControlTypeId

) {
}
