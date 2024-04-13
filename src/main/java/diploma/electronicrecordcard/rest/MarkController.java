package diploma.electronicrecordcard.rest;

import diploma.electronicrecordcard.data.dto.model.MarkDto;
import diploma.electronicrecordcard.service.MarkService;
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
@RequestMapping("api/marks")
public class MarkController {

    MarkService markService;

    @GetMapping("control-type/{id}")
    public ResponseEntity<List<MarkDto>> getByControlTypeId(@PathVariable("id") Short id) {
        return ResponseEntity.ok(markService.getByControlTypeId(id));
    }

}
