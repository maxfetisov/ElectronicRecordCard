package diploma.electronicrecordcard.rest;

import diploma.electronicrecordcard.data.dto.model.SubjectDto;
import diploma.electronicrecordcard.data.dto.request.SubjectCreateRequestDto;
import diploma.electronicrecordcard.service.SubjectService;
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

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("api/subjects")
public class SubjectController {

    SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<SubjectDto>> getAll() {
        return ResponseEntity.ok(subjectService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<SubjectDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(subjectService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SubjectDto> create(@RequestBody SubjectCreateRequestDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(subjectService.create(request));
    }

    @PutMapping
    public ResponseEntity<SubjectDto> update(@RequestBody SubjectDto request) {
        return ResponseEntity.ok(subjectService.update(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        subjectService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
