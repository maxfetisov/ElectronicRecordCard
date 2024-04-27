package diploma.electronicrecordcard.util;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class EntitySpecifications {

    private EntitySpecifications() {
        throw new AssertionError("Cannot be instantiated");
    }

    public static <T> Optional<Specification<T>> getSpecification(Map<String, Object> criteria, Class<T> entityClass) {
        List<Specification<T>> specifications = new ArrayList<>(criteria.size());
        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            specifications.add(getSpecification(entry.getKey(), entry.getValue(), entityClass));
        }
        return specifications.stream()
                .reduce(Specification::and);
    }

    public static <T> Specification<T> getSpecification(String propertyName, Object value, Class<T> entityClass) {
        return (root, _, criteriaBuilder) -> {
            String[] propertyNameParts = propertyName.split("\\.");
            if(propertyNameParts.length == 0) {
                return null;
            }
            if(propertyNameParts.length == 1) {
                return criteriaBuilder.equal(root.get(propertyNameParts[0]), value);
            }
            var join = root.join(propertyNameParts[0]);
            for(int i = 1; i < propertyNameParts.length - 1; i++) {
                join.join(propertyNameParts[i]);
            }
            return criteriaBuilder.equal(join.get(propertyNameParts[propertyNameParts.length - 1]), value);
        };
    }

}
