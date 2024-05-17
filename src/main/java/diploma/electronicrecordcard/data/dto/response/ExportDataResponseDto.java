package diploma.electronicrecordcard.data.dto.response;

import lombok.Builder;

@Builder
public record ExportDataResponseDto(

        String id,

        String student,

        String group,

        String discipline,

        String type,

        String value,

        String date,

        String note

) {
}
