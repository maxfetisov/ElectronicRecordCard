package diploma.electronicrecordcard.data.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UserSubjectControlTypeCreateRequestDto(
        @NotNull
        @Positive
        Short semester,

        @NotNull
        @Positive
        Short hoursNumber,

        @NotNull
        Long teacherId,

        @NotNull
        Long studentId,

        @NotNull
        Long subjectId,

        @NotNull
        Short controlTypeId
) {
}
