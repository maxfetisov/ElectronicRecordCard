package diploma.electronicrecordcard.repository.model;

import diploma.electronicrecordcard.data.entity.Deletion;
import diploma.electronicrecordcard.repository.VersionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletionRepository extends JpaRepository<Deletion, Long>,
        JpaSpecificationExecutor<Deletion>,
        VersionRepository<Deletion> {

    default Long getNextVersion() {
        return getNextVersion("deletion_version_sequence");
    }


}
