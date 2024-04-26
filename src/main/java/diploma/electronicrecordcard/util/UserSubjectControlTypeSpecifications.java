package diploma.electronicrecordcard.util;

import diploma.electronicrecordcard.data.entity.ControlType_;
import diploma.electronicrecordcard.data.entity.Subject_;
import diploma.electronicrecordcard.data.entity.UserSubjectControlType;
import diploma.electronicrecordcard.data.entity.UserSubjectControlType_;
import diploma.electronicrecordcard.data.entity.User_;
import org.springframework.data.jpa.domain.Specification;

public class UserSubjectControlTypeSpecifications {

    private UserSubjectControlTypeSpecifications() {
        throw new AssertionError("Cannot be instantiated");
    }

    public static Specification<UserSubjectControlType> getIdSpecification(Long id) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(UserSubjectControlType_.ID), id);
    }

    public static Specification<UserSubjectControlType> getSemesterSpecification(Short semester) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(UserSubjectControlType_.SEMESTER), semester);
    }

    public static Specification<UserSubjectControlType> getHoursNumberSpecification(Short hoursNumber) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(UserSubjectControlType_.HOURS_NUMBER), hoursNumber);
    }

    public static Specification<UserSubjectControlType> getControlTypeIdSpecification(Short id) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.join(UserSubjectControlType_.CONTROL_TYPE).get(ControlType_.ID), id);
    }

    public static Specification<UserSubjectControlType> getSubjectIdSpecification(Long id) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.join(UserSubjectControlType_.SUBJECT).get(Subject_.ID), id);
    }

    public static Specification<UserSubjectControlType> getTeacherIdSpecification(Long id) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.join(UserSubjectControlType_.TEACHER).get(User_.ID), id);
    }

    public static Specification<UserSubjectControlType> getStudentIdSpecification(Long id) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.join(UserSubjectControlType_.STUDENT).get(User_.ID), id);
    }

}
