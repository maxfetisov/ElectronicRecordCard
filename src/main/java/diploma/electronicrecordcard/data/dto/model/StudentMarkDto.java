package diploma.electronicrecordcard.data.dto.model;

import diploma.electronicrecordcard.data.Versionable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

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
        Long userSubjectControlTypeId,

        @NotNull
        @Getter
        Long version

) implements Versionable {
}
