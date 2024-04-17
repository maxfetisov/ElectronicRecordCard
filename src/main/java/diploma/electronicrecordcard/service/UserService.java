package diploma.electronicrecordcard.service;

import diploma.electronicrecordcard.data.dto.model.RoleDto;

import java.util.List;

public interface UserService {

    List<RoleDto> getUserRoles(Long id);

}
