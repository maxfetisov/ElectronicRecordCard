package diploma.electronicrecordcard.data.dto.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record UserSubjectControlTypeDto (

        @NotNull
        Long id,

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
){
}
