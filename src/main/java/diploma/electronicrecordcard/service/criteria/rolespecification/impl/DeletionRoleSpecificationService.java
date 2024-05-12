package diploma.electronicrecordcard.service.criteria.rolespecification.impl;

import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.entity.Deletion;
import diploma.electronicrecordcard.data.enumeration.RoleName;
import diploma.electronicrecordcard.service.criteria.rolespecification.RoleSpecificationService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class DeletionRoleSpecificationService implements RoleSpecificationService<Deletion> {

    @Override
    public Specification<Deletion> getSpecificationByRole(UserDto user, RoleName roleName) {
        return null;
    }

}
