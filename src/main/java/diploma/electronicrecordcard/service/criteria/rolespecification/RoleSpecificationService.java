package diploma.electronicrecordcard.service.criteria.rolespecification;

import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.enumeration.RoleName;
import org.springframework.data.jpa.domain.Specification;

@FunctionalInterface
public interface RoleSpecificationService<T> {

    Specification<T> getSpecificationByRole(UserDto user, RoleName roleName);

}
