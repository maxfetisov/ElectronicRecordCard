package diploma.electronicrecordcard.data.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record StudentMarkDto(
        Long id,
        Short semester,
        LocalDate completionDate,
        Short hoursNumber,
        Long teacherId,
        Long studentId,
        Long subjectId,
        Short controlTypeId,
        Short markId
) {
}
