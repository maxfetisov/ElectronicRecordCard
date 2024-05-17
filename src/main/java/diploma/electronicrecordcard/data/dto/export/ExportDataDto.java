package diploma.electronicrecordcard.data.dto.export;

import java.time.LocalDate;

public record ExportDataDto(

        String studentRecordBookNumber,

        String studentLastName,

        String studentFirstName,

        String studentMiddleName,

        String groupName,

        String subjectName,

        String controlTypeName,

        String markName,

        LocalDate completionDate,

        String note

) {
}
