package diploma.electronicrecordcard.data.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record GroupDto (

        Integer id,
        String name,
        String fullName,
        LocalDate admissionDate,
        Boolean deleted,
        Short instituteId
) {
}
