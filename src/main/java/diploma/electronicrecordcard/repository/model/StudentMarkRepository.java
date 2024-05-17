package diploma.electronicrecordcard.repository.model;

import diploma.electronicrecordcard.data.entity.StudentMark;
import diploma.electronicrecordcard.data.dto.export.ExportDataDto;
import diploma.electronicrecordcard.repository.VersionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMarkRepository extends JpaRepository<StudentMark, Long>,
        JpaSpecificationExecutor<StudentMark>,
        VersionRepository<StudentMark> {

    @Query("""
        select new diploma.electronicrecordcard.data.dto.export.ExportDataDto(
            u.recordBookNumber, u.lastName, u.firstName, u.middleName,
            g.name, s.name, ct.name, m.name, sm.completionDate, ''
        )
        from StudentMark sm
        join sm.userSubjectControlType usct
        join sm.mark m
        join usct.student u
        join usct.subject s
        join usct.controlType ct
        join u.group g
        where s.id = :subjectId and g.id = :groupId
    """)
    List<ExportDataDto> getExportData(@Param("subjectId") Long subjectId, @Param("groupId") Integer groupId);

    default Long getNextVersion() {
        return getNextVersion("student_mark_version_sequence");
    }

}
