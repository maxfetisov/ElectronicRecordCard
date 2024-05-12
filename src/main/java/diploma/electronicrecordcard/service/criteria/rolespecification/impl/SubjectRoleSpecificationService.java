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
            case STUDENT -> EntitySpecifications.getSpecification(
                    "userSubjectControlTypes.student.id",
                    user.id()
            );
            case TEACHER -> EntitySpecifications.getSpecification(
                    "userSubjectControlTypes.teacher.id",
                    user.id()
            );
            default -> null;
        };
    }

}
