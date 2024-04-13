package diploma.electronicrecordcard.repository;

import diploma.electronicrecordcard.data.entity.StudentMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMarkRepository extends JpaRepository<StudentMark, Long> {
}
