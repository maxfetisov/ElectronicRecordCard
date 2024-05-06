package diploma.electronicrecordcard.rest.model;

import diploma.electronicrecordcard.data.dto.model.RoleDto;
import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.dto.request.AuthenticationRequestDto;
import diploma.electronicrecordcard.data.dto.request.UserCreateRequestDto;
import diploma.electronicrecordcard.data.dto.request.UserUpdateRequestDto;
import diploma.electronicrecordcard.data.dto.response.AuthenticationResponseDto;
import diploma.electronicrecordcard.service.account.AccountService;
import diploma.electronicrecordcard.service.model.UserService;
import diploma.electronicrecordcard.service.version.impl.UserVersionService;
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
import java.util.Map;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

    UserService userService;

    UserVersionService userVersionService;

    AccountService accountService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("{id}/roles")
    public ResponseEntity<List<RoleDto>> getUserRoles(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserRoles(id));
    }

    @GetMapping("version/{version}")
    public ResponseEntity<List<UserDto>> getByVersion(@PathVariable("version") Long version) {
        return ResponseEntity.ok(userVersionService.getByVersion(version));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("login/{login}")
    public ResponseEntity<UserDto> getByLogin(@PathVariable("login") String login) {
        return ResponseEntity.ok(userService.getByLogin(login));
    }



    @PostMapping("filter")
    public ResponseEntity<List<UserDto>> getByCriteria(
            @RequestBody Map<String, Object> criteria
    ) {
        return ResponseEntity.ok(userService.getByCriteria(criteria));
    }

    @PostMapping("version/{version}/filter")
    public ResponseEntity<List<UserDto>> getByCriteriaAndVersion(
            @RequestBody Map<String, Object> criteria,
            @PathVariable("version") Long version
    ) {
        return ResponseEntity.ok(userService.getByCriteria(criteria, version));
    }

    @PostMapping("register")
    public ResponseEntity<UserDto> register(@RequestBody UserCreateRequestDto request) {
        return ResponseEntity.ok(accountService.registerUser(request));
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto request) {
        return ResponseEntity.ok(accountService.authenticateUser(request));
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody UserUpdateRequestDto request) {
        return ResponseEntity.ok(userService.update(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserDto> update(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.delete(id));
    }


}
