package diploma.electronicrecordcard.repository;

import diploma.electronicrecordcard.data.entity.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteRepository extends JpaRepository<Institute, Short> {
}
