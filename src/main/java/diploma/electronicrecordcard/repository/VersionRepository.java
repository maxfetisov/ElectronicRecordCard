package diploma.electronicrecordcard.repository;

import diploma.electronicrecordcard.data.Versionable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VersionRepository<T extends Versionable> {

    @Query("select nextval(:sequenceName)")
    Long getNextVersion(@Param("sequenceName") String sequenceName);

    List<T> findByVersionGreaterThan(Long version);

}
