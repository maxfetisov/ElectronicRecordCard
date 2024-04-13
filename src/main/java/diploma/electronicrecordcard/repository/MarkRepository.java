package diploma.electronicrecordcard.repository;

import diploma.electronicrecordcard.data.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Short> {

    @Query("select ct.marks from ControlType ct where ct.id = :id ")
    List<Mark> findByControlTypeId(@Param("id") Short id);

}
