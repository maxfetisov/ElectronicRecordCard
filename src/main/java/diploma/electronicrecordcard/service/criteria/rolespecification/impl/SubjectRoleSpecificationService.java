package diploma.electronicrecordcard.service.criteria.rolespecification.impl;

import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.entity.Subject;
import diploma.electronicrecordcard.data.enumeration.RoleName;
import diploma.electronicrecordcard.service.criteria.rolespecification.RoleSpecificationService;
import diploma.electronicrecordcard.util.EntitySpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SubjectRoleSpecificationService implements RoleSpecificationService<Subject> {

    @Override
    public Specification<Subject> getSpecificationByRole(UserDto user, RoleName roleName) {
        return switch (roleName) {
            case STUDENT -> EntitySpecifications.<Subject>getSpecification(
                            "userSubjectControlTypes.student.id",
                            user.id()
                    )
                    .and(EntitySpecifications.getSpecification(
                            "deleted",
                            false
                    ));
            case TEACHER -> EntitySpecifications.<Subject>getSpecification(
                            "userSubjectControlTypes.teacher.id",
                            user.id()
                    )
                    .and(EntitySpecifications.getSpecification(
                            "deleted",
                            false
                    ));
            case DEAN_OFFICE_EMPLOYEE -> EntitySpecifications.getSpecification(
                    "deleted",
                    false
            );
            default -> null;
        };
    }

}
