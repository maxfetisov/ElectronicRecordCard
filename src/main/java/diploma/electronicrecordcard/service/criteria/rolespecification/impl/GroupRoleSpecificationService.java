package diploma.electronicrecordcard.service.criteria.rolespecification.impl;

import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.entity.Group;
import diploma.electronicrecordcard.data.enumeration.RoleName;
import diploma.electronicrecordcard.service.criteria.rolespecification.RoleSpecificationService;
import diploma.electronicrecordcard.util.EntitySpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class GroupRoleSpecificationService implements RoleSpecificationService<Group> {

    @Override
    public Specification<Group> getSpecificationByRole(UserDto user, RoleName roleName) {
        return switch (roleName) {
            case STUDENT -> EntitySpecifications.<Group>getSpecification(
                            "users.id",
                            user.id()
                    )
                    .and(EntitySpecifications.getSpecification(
                            "deleted",
                            false
                    ));
            case TEACHER -> EntitySpecifications.<Group>getSpecification(
                            "users.studentUserSubjectControlTypes.teacher.id",
                            user.id()
                    )
                    .and(EntitySpecifications.getSpecification(
                            "deleted",
                            false
                    ));
            case DEAN_OFFICE_EMPLOYEE -> EntitySpecifications.<Group>getSpecification(
                            "institute.users.id",
                            user.id()
                    )
                    .and(EntitySpecifications.getSpecification(
                            "deleted",
                            false
                    ));
            default -> null;
        };
    }

}
