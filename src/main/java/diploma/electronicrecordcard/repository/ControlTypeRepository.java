package diploma.electronicrecordcard.repository;

import diploma.electronicrecordcard.data.entity.ControlType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ControlTypeRepository extends JpaRepository<ControlType, Short> {

    Optional<ControlType> findByName(String name);

}
