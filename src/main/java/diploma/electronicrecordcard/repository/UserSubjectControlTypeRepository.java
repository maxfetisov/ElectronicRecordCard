package diploma.electronicrecordcard.repository;

import diploma.electronicrecordcard.data.entity.UserSubjectControlType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubjectControlTypeRepository extends JpaRepository<UserSubjectControlType, Long>,
        JpaSpecificationExecutor<UserSubjectControlType> {
}
