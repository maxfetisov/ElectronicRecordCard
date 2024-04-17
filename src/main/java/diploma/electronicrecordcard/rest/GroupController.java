package diploma.electronicrecordcard.rest;

import diploma.electronicrecordcard.data.dto.model.GroupDto;
import diploma.electronicrecordcard.data.dto.request.GroupCreateRequestDto;
import diploma.electronicrecordcard.data.dto.request.GroupUpdateRequestDto;
import diploma.electronicrecordcard.service.GroupService;
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
@RequestMapping("api/groups")
public class GroupController {

    GroupService groupService;

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAll() {
        return ResponseEntity.ok(groupService.getAll());
    }

    @GetMapping("institute/{id}")
    public ResponseEntity<List<GroupDto>> getByInstituteId(@PathVariable("id") Short id) {
        return ResponseEntity.ok(groupService.getByInstituteId(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<GroupDto> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(groupService.getById(id));
    }

    @PostMapping
    public ResponseEntity<GroupDto> create(@RequestBody GroupCreateRequestDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(groupService.create(request));
    }

    @PutMapping
    public ResponseEntity<GroupDto> update(@RequestBody GroupUpdateRequestDto request) {
        return ResponseEntity.ok(groupService.update(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GroupDto> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(groupService.delete(id));
    }

}
