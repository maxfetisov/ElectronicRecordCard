package diploma.electronicrecordcard.rest;

import diploma.electronicrecordcard.data.dto.model.MarkDto;
import diploma.electronicrecordcard.data.dto.request.MarkUpdateRequestDto;
import diploma.electronicrecordcard.service.MarkService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("api/marks")
public class MarkController {

    MarkService markService;

    @GetMapping
    public ResponseEntity<List<MarkDto>> getAll() {
        return ResponseEntity.ok(markService.getAll());
    }

    @GetMapping("control-type/{id}")
    public ResponseEntity<List<MarkDto>> getByControlTypeId(@PathVariable("id") Short id) {
        return ResponseEntity.ok(markService.getByControlTypeId(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<MarkDto> getById(@PathVariable("id") Short id) {
        return ResponseEntity.ok(markService.getById(id));
    }

    @GetMapping("version/{version}")
    public ResponseEntity<List<MarkDto>> getByVersion(@PathVariable("version") Long version) {
        return ResponseEntity.ok(markService.getByVersion(version));
    }

    @PutMapping
    public ResponseEntity<MarkDto> update(@Valid @RequestBody MarkUpdateRequestDto request) {
        return ResponseEntity.ok(markService.update(request));
    }

}
