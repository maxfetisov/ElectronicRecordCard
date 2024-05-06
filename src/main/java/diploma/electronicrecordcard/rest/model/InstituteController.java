package diploma.electronicrecordcard.rest.model;

import diploma.electronicrecordcard.data.dto.model.InstituteDto;
import diploma.electronicrecordcard.data.dto.request.InstituteCreateRequestDto;
import diploma.electronicrecordcard.service.model.InstituteService;
import diploma.electronicrecordcard.service.version.impl.InstituteVersionService;
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
@RequestMapping("api/institutes")
public class InstituteController {

    InstituteService instituteService;

    InstituteVersionService instituteVersionService;

    @GetMapping
    public ResponseEntity<List<InstituteDto>> getAll() {
        return ResponseEntity.ok(instituteService.findAll());
    }

    @GetMapping("version/{version}")
    public ResponseEntity<List<InstituteDto>> getByVersion(@PathVariable("version") Long version) {
        return ResponseEntity.ok(instituteVersionService.getByVersion(version));
    }

    @GetMapping("{id}")
    public ResponseEntity<InstituteDto> getById(@PathVariable("id") Short id) {
        return ResponseEntity.ok(instituteService.findById(id));
    }

    @PostMapping("filter")
    public ResponseEntity<List<InstituteDto>> getByCriteria(
            @RequestBody Map<String, Object> criteria
    ) {
        return ResponseEntity.ok(instituteService.getByCriteria(criteria));
    }

    @PostMapping("version/{version}/filter")
    public ResponseEntity<List<InstituteDto>> getByCriteriaAndVersion(
            @RequestBody Map<String, Object> criteria,
            @PathVariable("version") Long version
    ) {
        return ResponseEntity.ok(instituteService.getByCriteria(criteria, version));
    }

    @PostMapping
    public ResponseEntity<InstituteDto> create(@Valid @RequestBody InstituteCreateRequestDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(instituteService.create(request));
    }

    @PutMapping
    public ResponseEntity<InstituteDto> update(@Valid @RequestBody InstituteDto request) {
        return ResponseEntity.ok(instituteService.update(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Short id) {
        instituteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
