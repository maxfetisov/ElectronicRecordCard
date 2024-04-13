package diploma.electronicrecordcard.data.dto.model;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record StudentMarkDto(
        Long id,
        LocalDate completionDate,
        Short markId,
        Long userSubjectControlTypeId
) {
}
