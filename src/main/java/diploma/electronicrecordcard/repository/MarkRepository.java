package diploma.electronicrecordcard.repository;

import diploma.electronicrecordcard.data.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Short> {
}
