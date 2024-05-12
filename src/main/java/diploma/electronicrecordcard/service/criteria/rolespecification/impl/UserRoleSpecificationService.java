package diploma.electronicrecordcard.service.criteria.rolespecification.impl;

import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.entity.User;
import diploma.electronicrecordcard.data.enumeration.RoleName;
import diploma.electronicrecordcard.service.criteria.rolespecification.RoleSpecificationService;
import diploma.electronicrecordcard.util.EntitySpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserRoleSpecificationService implements RoleSpecificationService<User> {

    @Override
    public Specification<User> getSpecificationByRole(UserDto user, RoleName roleName) {
        return switch (roleName) {
            case STUDENT -> EntitySpecifications.<User>getSpecification(
                            "teacherUserSubjectControlTypes.student.id",
                            user.id()
                    )
                    .or(EntitySpecifications.getSpecification(
                            "id",
                            user.id()
                    ))
                    .and(EntitySpecifications.getSpecification(
                            "deleted",
                            false
                    ));
            case TEACHER -> EntitySpecifications.<User>getSpecification(
                            "studentUserSubjectControlTypes.teacher.id",
                            user.id()
                    )
                    .or(EntitySpecifications.getSpecification(
                            "id",
                            user.id()
                    ))
                    .and(EntitySpecifications.getSpecification(
                            "deleted",
                            false
                    ));
            case DEAN_OFFICE_EMPLOYEE -> EntitySpecifications.<User>getSpecification(
                            "institute.users.id",
                            user.id()
                    )
                    .or(EntitySpecifications.getSpecification(
                            "id",
                            user.id()
                    ))
                    .and(EntitySpecifications.getSpecification(
                            "deleted",
                            false
                    ));
            default -> null;
        };
    }

}
