package diploma.electronicrecordcard.service.criteria;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CriteriaService<T> {

    List<T> getByCriteria(Specification<T> specification);

}
