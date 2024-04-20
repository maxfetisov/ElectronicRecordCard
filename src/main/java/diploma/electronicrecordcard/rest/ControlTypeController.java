package diploma.electronicrecordcard.rest;

import diploma.electronicrecordcard.data.dto.model.ControlTypeDto;
import diploma.electronicrecordcard.data.dto.request.ControlTypeUpdateRequestDto;
import diploma.electronicrecordcard.data.dto.response.ControlTypeMarkResponseDto;
import diploma.electronicrecordcard.service.ControlTypeService;
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
@RequestMapping("api/control-types")
public class ControlTypeController {

    ControlTypeService controlTypeService;

    @GetMapping
    public ResponseEntity<List<ControlTypeDto>> getAll() {
        return ResponseEntity.ok(controlTypeService.getAll());
    }

    @GetMapping("with-marks")
    public ResponseEntity<List<ControlTypeMarkResponseDto>> getAllWithMarks() {
        return ResponseEntity.ok(controlTypeService.getAllWithMarks());
    }


    @GetMapping("{id}")
    public ResponseEntity<ControlTypeDto> getById(@PathVariable("id") Short id) {
        return ResponseEntity.ok(controlTypeService.getById(id));
    }

    @GetMapping("name/{name}")
    public ResponseEntity<ControlTypeDto> getByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(controlTypeService.getByName(name));
    }

    @PutMapping
    public ResponseEntity<ControlTypeDto> update(@Valid @RequestBody ControlTypeUpdateRequestDto request) {
        return ResponseEntity.ok(controlTypeService.update(request));
    }

}
