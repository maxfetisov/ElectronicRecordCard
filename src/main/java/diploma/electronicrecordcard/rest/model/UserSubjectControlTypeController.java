package diploma.electronicrecordcard.rest.model;

import diploma.electronicrecordcard.data.dto.model.UserSubjectControlTypeDto;
import diploma.electronicrecordcard.data.dto.request.UserSubjectControlTypeCreateRequestDto;
import diploma.electronicrecordcard.service.model.UserSubjectControlTypeService;
import diploma.electronicrecordcard.service.version.impl.UserSubjectControlTypeVersionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
@RequestMapping("api/user-subject-control-types")
public class UserSubjectControlTypeController {

    UserSubjectControlTypeService userSubjectControlTypeService;

    UserSubjectControlTypeVersionService userSubjectControlTypeVersionService;

    @GetMapping
    public ResponseEntity<List<UserSubjectControlTypeDto>> getAll() {
        return ResponseEntity.ok(userSubjectControlTypeService.getAll());
    }

    @GetMapping("version/{version}")
    public ResponseEntity<List<UserSubjectControlTypeDto>> getByVersion(@PathVariable("version") Long version) {
        return ResponseEntity.ok(userSubjectControlTypeVersionService.getByVersion(version));
    }

    @Deprecated
    @PostMapping("filter")
    public ResponseEntity<List<UserSubjectControlTypeDto>> getByCriteria(
            @RequestBody UserSubjectControlTypeDto criteria
    ) {
        return ResponseEntity.ok(userSubjectControlTypeService.getByCriteria(criteria));
    }

    @PostMapping("filter/v2")
    public ResponseEntity<List<UserSubjectControlTypeDto>> getByCriteria(
            @RequestBody Map<String, Object> criteria
    ) {
        return ResponseEntity.ok(userSubjectControlTypeService.getByCriteria(criteria));
    }

    @PostMapping("version/{version}/filter")
    public ResponseEntity<List<UserSubjectControlTypeDto>> getByCriteriaAndVersion(
            @RequestBody Map<String, Object> criteria,
            @PathVariable("version") Long version
    ) {
        return ResponseEntity.ok(userSubjectControlTypeService.getByCriteria(criteria, version));
    }

    @PostMapping
    public ResponseEntity<UserSubjectControlTypeDto> create(
            @RequestBody UserSubjectControlTypeCreateRequestDto request
    ) {
        return ResponseEntity.ok(userSubjectControlTypeService.create(request));
    }

    @PutMapping
    public ResponseEntity<UserSubjectControlTypeDto> update(
            @RequestBody UserSubjectControlTypeDto request
    ) {
        return ResponseEntity.ok(userSubjectControlTypeService.update(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserSubjectControlTypeDto> delete(
            @PathVariable("id") Long id,
            @RequestParam(value = "version", defaultValue = "1") Long version
    ) {
        userSubjectControlTypeService.delete(id, version);
        return ResponseEntity.noContent().build();
    }

}
