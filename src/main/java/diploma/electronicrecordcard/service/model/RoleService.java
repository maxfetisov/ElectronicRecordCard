package diploma.electronicrecordcard.service.model;

import diploma.electronicrecordcard.data.dto.model.RoleDto;
import diploma.electronicrecordcard.data.enumeration.RoleName;

import java.util.List;

public interface RoleService {

    List<RoleDto> getAll();

    RoleDto getByName(RoleName name);

}
