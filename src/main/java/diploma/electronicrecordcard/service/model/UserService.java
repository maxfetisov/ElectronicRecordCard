package diploma.electronicrecordcard.service.model;

import diploma.electronicrecordcard.data.dto.model.RoleDto;
import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.dto.request.UserUpdateRequestDto;
import diploma.electronicrecordcard.service.criteria.CriteriaAndVersionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService, CriteriaAndVersionService<UserDto> {

    List<UserDto> getAll();

    Page<UserDto> getAll(Pageable pageable);

    UserDto getById(Long id);

    UserDto getByLogin(String login);

    List<RoleDto> getUserRoles(Long id);

    UserDto create(UserDto userDto);

    UserDto update(UserUpdateRequestDto userDto);

    UserDto delete(Long id, Long version);

}
