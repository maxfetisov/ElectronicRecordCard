package diploma.electronicrecordcard.repository;

import diploma.electronicrecordcard.data.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    List<Group> findByInstituteId(Short id);

}
