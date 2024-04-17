package diploma.electronicrecordcard.data.dto.request;

import java.time.LocalDate;

public record GroupCreateRequestDto(

        String name,
        String fullName,
        LocalDate admissionDate,
        Short instituteId
) {
}
