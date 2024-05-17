package diploma.electronicrecordcard.rest.export;

import diploma.electronicrecordcard.data.dto.response.ExportDataResponseDto;
import diploma.electronicrecordcard.service.export.ExportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("api/export")
public class ExportController {

    ExportService exportService;

    @GetMapping
    public ResponseEntity<List<ExportDataResponseDto>> export(@RequestParam("subjectId") Long subjectId,
                                                              @RequestParam("groupId") Integer groupId) {
        return ResponseEntity.ok(exportService.export(subjectId, groupId));
    }

}
