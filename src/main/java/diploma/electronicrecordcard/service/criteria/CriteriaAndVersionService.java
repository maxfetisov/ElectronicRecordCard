package diploma.electronicrecordcard.service.criteria;

import java.util.List;
import java.util.Map;

public interface CriteriaAndVersionService<T> {

    List<T> getByCriteria(Map<String, Object> criteria);

    List<T> getByCriteria(Map<String, Object> criteria, Long version);

}
