package diploma.electronicrecordcard.service.criteria.rolespecification.impl;

import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.entity.Institute;
import diploma.electronicrecordcard.data.enumeration.RoleName;
import diploma.electronicrecordcard.service.criteria.rolespecification.RoleSpecificationService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class InstituteRoleSpecificationService implements RoleSpecificationService<Institute> {

    @Override
    public Specification<Institute> getSpecificationByRole(UserDto user, RoleName roleName) {
        return null;
    }

}
