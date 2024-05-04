package diploma.electronicrecordcard.repository.model;

import diploma.electronicrecordcard.data.entity.Subject;
import diploma.electronicrecordcard.repository.VersionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>, VersionRepository<Subject> {

    default Long getNextVersion() {
        return getNextVersion("subject_version_sequence");
    }

}
