package diploma.electronicrecordcard.rest.model;

import diploma.electronicrecordcard.data.dto.model.SubjectDto;
import diploma.electronicrecordcard.data.dto.request.SubjectCreateRequestDto;
import diploma.electronicrecordcard.service.model.SubjectService;
import diploma.electronicrecordcard.service.version.impl.SubjectVersionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("api/subjects")
public class SubjectController {

    SubjectService subjectService;

    SubjectVersionService subjectVersionService;

    @GetMapping
    public ResponseEntity<List<SubjectDto>> getAll() {
        return ResponseEntity.ok(subjectService.getAll());
    }

    @GetMapping("version/{version}")
    public ResponseEntity<List<SubjectDto>> getByVersion(@PathVariable("version") Long version) {
        return ResponseEntity.ok(subjectVersionService.getByVersion(version));
    }

    @GetMapping("{id}")
    public ResponseEntity<SubjectDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(subjectService.getById(id));
    }

    @PostMapping("filter")
    public ResponseEntity<List<SubjectDto>> getByCriteria(
            @RequestBody Map<String, Object> criteria
    ) {
        return ResponseEntity.ok(subjectService.getByCriteria(criteria));
    }

    @PostMapping("version/{version}/filter")
    public ResponseEntity<List<SubjectDto>> getByCriteriaAndVersion(
            @RequestBody Map<String, Object> criteria,
            @PathVariable("version") Long version
    ) {
        return ResponseEntity.ok(subjectService.getByCriteria(criteria, version));
    }

    @PostMapping
    public ResponseEntity<SubjectDto> create(@Valid @RequestBody SubjectCreateRequestDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(subjectService.create(request));
    }

    @PutMapping
    public ResponseEntity<SubjectDto> update(@Valid @RequestBody SubjectDto request) {
        return ResponseEntity.ok(subjectService.update(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        subjectService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
