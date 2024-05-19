package diploma.electronicrecordcard.data.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UserSubjectControlTypeCreateByGroupRequest (

        @NotNull
        @Positive
        Short semester,

        @NotNull
        @Positive
        Short hoursNumber,

        @NotNull
        Long teacherId,

        @NotNull
        Long groupId,

        @NotNull
        Long subjectId,

        @NotNull
        Short controlTypeId

) {
}
