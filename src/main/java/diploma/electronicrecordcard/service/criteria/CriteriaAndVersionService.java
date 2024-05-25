package diploma.electronicrecordcard.service.criteria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CriteriaAndVersionService<T> {

    List<T> getByCriteria(Map<String, Object> criteria);

    List<T> getByCriteria(Map<String, Object> criteria, Long version);

    Page<T> getByCriteria(Map<String, Object> criteria, Pageable pageable);

    Page<T> getByCriteria(Map<String, Object> criteria, Long version, Pageable pageable);

}
