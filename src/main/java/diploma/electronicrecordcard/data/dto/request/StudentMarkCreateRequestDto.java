package diploma.electronicrecordcard.data.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record StudentMarkCreateRequestDto (

        @NotNull
        LocalDate completionDate,

        @NotNull
        Short markId,

        @NotNull
        Long userSubjectControlTypeId

) {
}
