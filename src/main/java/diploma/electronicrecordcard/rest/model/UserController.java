package diploma.electronicrecordcard.rest.model;

import diploma.electronicrecordcard.data.dto.model.RoleDto;
import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.dto.request.AuthenticationRequestDto;
import diploma.electronicrecordcard.data.dto.request.RefreshRequestDto;
import diploma.electronicrecordcard.data.dto.request.UserCreateRequestDto;
import diploma.electronicrecordcard.data.dto.request.UserUpdateRequestDto;
import diploma.electronicrecordcard.data.dto.response.AuthenticationResponseDto;
import diploma.electronicrecordcard.service.account.AccountService;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.model.UserService;
import diploma.electronicrecordcard.service.version.impl.UserVersionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    AuthorityService authorityService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("page")
    public ResponseEntity<Page<UserDto>> getAll(@RequestParam("pageNumber") int pageNumber,
                                                @RequestParam("pageSize") int pageSize) {
        return ResponseEntity.ok(userService.getAll(PageRequest.of(pageNumber, pageSize)));
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

    @GetMapping("current")
    public ResponseEntity<UserDto> getCurrent() {
        return ResponseEntity.ok(authorityService.getCurrentUser());
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

    @PostMapping("filter/page")
    public ResponseEntity<Page<UserDto>> getByCriteria(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize,
            @RequestBody Map<String, Object> criteria
    ) {
        return ResponseEntity.ok(userService.getByCriteria(criteria, PageRequest.of(pageNumber, pageSize)));
    }

    @PostMapping("version/{version}/filter/page")
    public ResponseEntity<Page<UserDto>> getByCriteriaAndVersion(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize,
            @RequestBody Map<String, Object> criteria,
            @PathVariable("version") Long version
    ) {
        return ResponseEntity.ok(userService.getByCriteria(criteria, version, PageRequest.of(pageNumber, pageSize)));
    }


    @PostMapping("register")
    public ResponseEntity<UserDto> register(@RequestBody UserCreateRequestDto request) {
        return ResponseEntity.ok(accountService.register(request));
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto request) {
        return ResponseEntity.ok(accountService.authenticate(request));
    }

    @PostMapping("refresh")
    public ResponseEntity<AuthenticationResponseDto> refresh(@RequestBody RefreshRequestDto request) {
        return ResponseEntity.ok(accountService.refresh(request));
    }

    @PostMapping("logout")
    public ResponseEntity<Void> logout() {
        accountService.logout();
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody UserUpdateRequestDto request) {
        return ResponseEntity.ok(userService.update(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserDto> update(@PathVariable("id") Long id,
                                          @RequestParam(value = "version", defaultValue = "1") Long version) {
        return ResponseEntity.ok(userService.delete(id, version));
    }


}
