package diploma.electronicrecordcard.rest.model;

import diploma.electronicrecordcard.data.dto.model.DeletionDto;
import diploma.electronicrecordcard.service.model.DeletionService;
import diploma.electronicrecordcard.service.version.impl.DeletionVersionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("api/deletions")
public class DeletionController {

    DeletionService deletionService;

    DeletionVersionService deletionVersionService;

    @GetMapping
    public ResponseEntity<List<DeletionDto>> getAll() {
        return ResponseEntity.ok(deletionService.getAll());
    }

    @GetMapping("version/{version}")
    public ResponseEntity<List<DeletionDto>> getByVersion(@PathVariable("version") Long version) {
        return ResponseEntity.ok(deletionVersionService.getByVersion(version));
    }

    @PostMapping("filter")
    public ResponseEntity<List<DeletionDto>> getByCriteria(
            @RequestBody Map<String, Object> criteria
    ) {
        return ResponseEntity.ok(deletionService.getByCriteria(criteria));
    }

    @PostMapping("version/{version}/filter")
    public ResponseEntity<List<DeletionDto>> getByCriteriaAndVersion(
            @RequestBody Map<String, Object> criteria,
            @PathVariable("version") Long version
    ) {
        return ResponseEntity.ok(deletionService.getByCriteria(criteria, version));
    }

}
