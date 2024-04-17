package diploma.electronicrecordcard.rest;

import diploma.electronicrecordcard.data.dto.model.InstituteDto;
import diploma.electronicrecordcard.data.dto.request.InstituteCreateRequestDto;
import diploma.electronicrecordcard.service.InstituteService;
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

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("api/institutes")
public class InstituteController {

    InstituteService instituteService;

    @GetMapping
    public ResponseEntity<List<InstituteDto>> getAll() {
        return ResponseEntity.ok(instituteService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<InstituteDto> getById(@PathVariable("id") Short id) {
        return ResponseEntity.ok(instituteService.findById(id));
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
        return ResponseEntity.ok().build();
    }

}
