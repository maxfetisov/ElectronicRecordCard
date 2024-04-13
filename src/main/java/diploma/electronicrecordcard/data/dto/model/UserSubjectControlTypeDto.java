package diploma.electronicrecordcard.data.dto.model;

import lombok.Builder;

@Builder
public record UserSubjectControlTypeDto (
        Long id,
        Short semester,
        Short hoursNumber,
        Long teacherId,
        Long studentId,
        Long subjectId,
        Short controlTypeId
){
}
