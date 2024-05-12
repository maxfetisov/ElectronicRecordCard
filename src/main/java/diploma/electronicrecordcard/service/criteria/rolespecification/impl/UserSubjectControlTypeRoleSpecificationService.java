package diploma.electronicrecordcard.service.criteria.rolespecification.impl;

import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.entity.UserSubjectControlType;
import diploma.electronicrecordcard.data.enumeration.RoleName;
import diploma.electronicrecordcard.service.criteria.rolespecification.RoleSpecificationService;
import diploma.electronicrecordcard.util.EntitySpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserSubjectControlTypeRoleSpecificationService
        implements RoleSpecificationService<UserSubjectControlType> {

    @Override
    public Specification<UserSubjectControlType> getSpecificationByRole(UserDto user, RoleName roleName) {
        return switch (roleName) {
            case STUDENT -> EntitySpecifications.getSpecification(
                            "student.id",
                            user.id()
                    );
            case TEACHER -> EntitySpecifications.getSpecification(
                            "teacher.id",
                            user.id()
                    );
            case DEAN_OFFICE_EMPLOYEE -> EntitySpecifications.<UserSubjectControlType>getSpecification(
                            "student.institute.users.id",
                            user.id()
                    )
                    .or(EntitySpecifications.getSpecification(
                            "teacher.institute.users.id",
                            user.id()
                    ));
            default -> null;
        };
    }

}
