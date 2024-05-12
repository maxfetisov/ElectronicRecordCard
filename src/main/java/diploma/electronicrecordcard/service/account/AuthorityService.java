package diploma.electronicrecordcard.service.account;

import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.enumeration.RoleName;

import java.util.List;

public interface AuthorityService {

    UserDto getCurrentUser();

    boolean hasAnyAuthority(List<RoleName> roles);

    void checkRolesAndThrow(List<RoleName> roles);
}
