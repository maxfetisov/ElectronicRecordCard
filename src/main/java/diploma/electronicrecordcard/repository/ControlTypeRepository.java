package diploma.electronicrecordcard.repository;

import diploma.electronicrecordcard.data.entity.ControlType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlTypeRepository extends JpaRepository<ControlType, Short> {
}
