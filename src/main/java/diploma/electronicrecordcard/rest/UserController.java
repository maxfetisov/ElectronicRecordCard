package diploma.electronicrecordcard.rest;

import diploma.electronicrecordcard.data.dto.model.RoleDto;
import diploma.electronicrecordcard.service.UserService;
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
@RequestMapping("api/users")
public class UserController {

    UserService userService;

    @GetMapping("{id}/roles")
    public ResponseEntity<List<RoleDto>> getUserRoles(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserRoles(id));
    }

}
