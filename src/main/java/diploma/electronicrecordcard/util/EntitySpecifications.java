package diploma.electronicrecordcard.util;

import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class EntitySpecifications {

    private EntitySpecifications() {
        throw new AssertionError("Cannot be instantiated");
    }

    public static <T> Optional<Specification<T>> getSpecification(Map<String, Object> criteria) {
        List<Specification<T>> specifications = new ArrayList<>(criteria.size());
        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            specifications.add(getSpecification(entry.getKey(), entry.getValue()));
        }
        return specifications.stream()
                .reduce(Specification::and);
    }

    public static <T> Specification<T> getSpecification(String propertyName, Object value) {
        return (root, query, criteriaBuilder) -> {
            String[] propertyNameParts = propertyName.split("\\.");
            if(propertyNameParts.length == 0) {
                return null;
            }
            if(propertyNameParts.length == 1) {
                return criteriaBuilder.equal(root.get(propertyNameParts[0]), value);
            }
            var join = root.join(propertyNameParts[0], JoinType.LEFT);
            for(int i = 1; i < propertyNameParts.length - 1; i++) {
                join = join.join(propertyNameParts[i], JoinType.LEFT);
            }
            return criteriaBuilder.equal(join.get(propertyNameParts[propertyNameParts.length - 1]), value);
        };
    }

}
