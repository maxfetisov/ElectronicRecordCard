package diploma.electronicrecordcard.rest;

import diploma.electronicrecordcard.data.dto.model.InstituteDto;
import diploma.electronicrecordcard.service.InstituteService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

}
