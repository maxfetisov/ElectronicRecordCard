package diploma.electronicrecordcard.repository.model;

import diploma.electronicrecordcard.data.entity.UserSubjectControlType;
import diploma.electronicrecordcard.repository.VersionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubjectControlTypeRepository extends JpaRepository<UserSubjectControlType, Long>,
        JpaSpecificationExecutor<UserSubjectControlType>,
        VersionRepository<UserSubjectControlType> {

    default Long getNextVersion() {
        return getNextVersion("user_subject_control_type_version_sequence");
    }

}
