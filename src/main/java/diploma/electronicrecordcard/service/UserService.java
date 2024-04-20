package diploma.electronicrecordcard.service;

import diploma.electronicrecordcard.data.dto.model.RoleDto;
import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.dto.request.UserCreateRequestDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto getByLogin(String login);

    List<RoleDto> getUserRoles(Long id);

    UserDto createUser(UserCreateRequestDto userDto);

}
