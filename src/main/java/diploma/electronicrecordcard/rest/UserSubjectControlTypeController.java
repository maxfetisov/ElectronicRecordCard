package diploma.electronicrecordcard.rest;

import diploma.electronicrecordcard.data.dto.model.UserSubjectControlTypeDto;
import diploma.electronicrecordcard.data.dto.request.UserSubjectControlTypeCreateRequestDto;
import diploma.electronicrecordcard.service.UserSubjectControlTypeService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("api/user-subject-control-types")
public class UserSubjectControlTypeController {

    UserSubjectControlTypeService userSubjectControlTypeService;

    @GetMapping
    public ResponseEntity<List<UserSubjectControlTypeDto>> getAll() {
        return ResponseEntity.ok(userSubjectControlTypeService.getAll());
    }

    @PostMapping("filter")
    public ResponseEntity<List<UserSubjectControlTypeDto>> getByCriteria(
            @RequestBody UserSubjectControlTypeDto criteria
    ) {
        return ResponseEntity.ok(userSubjectControlTypeService.getByCriteria(criteria));
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
    public ResponseEntity<UserSubjectControlTypeDto> delete(@PathVariable("id") Long id) {
        userSubjectControlTypeService.delete(id);
        return ResponseEntity.ok().build();
    }

}
