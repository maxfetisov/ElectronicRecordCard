package diploma.electronicrecordcard.data.dto.request;

import java.time.LocalDate;

public record GroupUpdateRequestDto(
        Integer id,
        String name,
        String fullName,
        LocalDate admissionDate,
        Short instituteId
) {
}
