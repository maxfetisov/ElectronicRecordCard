package diploma.electronicrecordcard.repository.model;

import diploma.electronicrecordcard.data.entity.StudentMark;
import diploma.electronicrecordcard.repository.VersionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMarkRepository extends JpaRepository<StudentMark, Long>,
        JpaSpecificationExecutor<StudentMark>,
        VersionRepository<StudentMark> {

    default Long getNextVersion() {
        return getNextVersion("student_mark_version_sequence");
    }

}
