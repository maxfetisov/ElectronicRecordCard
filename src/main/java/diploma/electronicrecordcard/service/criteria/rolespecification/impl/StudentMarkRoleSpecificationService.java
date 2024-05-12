package diploma.electronicrecordcard.service.criteria.rolespecification.impl;

import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.entity.StudentMark;
import diploma.electronicrecordcard.data.enumeration.RoleName;
import diploma.electronicrecordcard.service.criteria.rolespecification.RoleSpecificationService;
import diploma.electronicrecordcard.util.EntitySpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class StudentMarkRoleSpecificationService implements RoleSpecificationService<StudentMark> {

    @Override
    public Specification<StudentMark> getSpecificationByRole(UserDto user, RoleName roleName) {
        return switch (roleName) {
            case STUDENT -> EntitySpecifications.getSpecification(
                    "userSubjectControlType.student.id",
                    user.id()
            );
            case TEACHER -> EntitySpecifications.getSpecification(
                    "userSubjectControlType.teacher.id",
                    user.id()
            );
            case DEAN_OFFICE_EMPLOYEE -> EntitySpecifications.<StudentMark>getSpecification(
                            "userSubjectControlType.student.institute.users.id",
                            user.id()
                    )
                    .or(EntitySpecifications.getSpecification(
                            "userSubjectControlType.teacher.institute.users.id",
                            user.id()
                    ));
            default -> null;
        };
    }

}
