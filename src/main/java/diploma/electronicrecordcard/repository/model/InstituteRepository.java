package diploma.electronicrecordcard.repository.model;

import diploma.electronicrecordcard.data.entity.Institute;
import diploma.electronicrecordcard.repository.VersionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteRepository extends JpaRepository<Institute, Short>,
        JpaSpecificationExecutor<Institute>,
        VersionRepository<Institute> {

    default Long getNextVersion() {
        return getNextVersion("institute_version_sequence");
    }

}
