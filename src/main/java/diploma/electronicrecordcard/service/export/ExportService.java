package diploma.electronicrecordcard.service.export;

import diploma.electronicrecordcard.data.dto.response.ExportDataResponseDto;

import java.util.List;

public interface ExportService {

    List<ExportDataResponseDto> export(Long subjectId, Integer groupId);

}
