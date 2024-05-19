package diploma.electronicrecordcard.rest.model;

import diploma.electronicrecordcard.data.dto.model.StudentMarkDto;
import diploma.electronicrecordcard.data.dto.request.StudentMarkCreateRequestDto;
import diploma.electronicrecordcard.service.model.StudentMarkService;
import diploma.electronicrecordcard.service.version.impl.StudentMarkVersionService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("api/student-marks")
public class StudentMarkController {

    StudentMarkService studentMarkService;

    StudentMarkVersionService studentMarkVersionService;

    @GetMapping
    public ResponseEntity<List<StudentMarkDto>> getAll() {
        return ResponseEntity.ok(studentMarkService.getAll());
    }

    @GetMapping("version/{version}")
    public ResponseEntity<List<StudentMarkDto>> getByVersion(@PathVariable("version") Long version) {
        return ResponseEntity.ok(studentMarkVersionService.getByVersion(version));
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentMarkDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(studentMarkService.getById(id));
    }

    @PostMapping("filter")
    public ResponseEntity<List<StudentMarkDto>> getByCriteria(
            @RequestBody Map<String, Object> criteria
    ) {
        return ResponseEntity.ok(studentMarkService.getByCriteria(criteria));
    }

    @PostMapping("version/{version}/filter")
    public ResponseEntity<List<StudentMarkDto>> getByCriteriaAndVersion(
            @RequestBody Map<String, Object> criteria,
            @PathVariable("version") Long version
    ) {
        return ResponseEntity.ok(studentMarkService.getByCriteria(criteria, version));
    }

    @PostMapping
    public ResponseEntity<StudentMarkDto> create(@Valid @RequestBody StudentMarkCreateRequestDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentMarkService.create(request));
    }

    @PutMapping
    public ResponseEntity<StudentMarkDto> update(@Valid @RequestBody StudentMarkDto request) {
        return ResponseEntity.ok(studentMarkService.update(request));
    }

    @PostMapping("all")
    public ResponseEntity<List<StudentMarkDto>> createOrUpdate(@RequestBody List<StudentMarkDto> request) {
        return ResponseEntity.ok(studentMarkService.createOrUpdate(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id,
                                       @RequestParam(value = "version", defaultValue = "1") Long version) {
        studentMarkService.delete(id, version);
        return ResponseEntity.noContent().build();
    }

}
