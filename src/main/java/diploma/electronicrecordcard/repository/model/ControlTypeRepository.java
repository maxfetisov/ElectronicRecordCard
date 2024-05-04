package diploma.electronicrecordcard.repository.model;

import diploma.electronicrecordcard.data.entity.ControlType;
import diploma.electronicrecordcard.repository.VersionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ControlTypeRepository extends JpaRepository<ControlType, Short>, VersionRepository<ControlType> {

    Optional<ControlType> findByName(String name);

    default Long getNextVersion() {
        return getNextVersion("control_type_version_sequence");
    }

}
