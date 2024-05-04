package diploma.electronicrecordcard.repository.model;

import diploma.electronicrecordcard.data.entity.Group;
import diploma.electronicrecordcard.repository.VersionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer>, VersionRepository<Group> {

    List<Group> findByInstituteId(Short id);

    default Long getNextVersion() {
        return getNextVersion("group_version_sequence");
    }

}
