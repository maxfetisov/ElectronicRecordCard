package diploma.electronicrecordcard.util;

import diploma.electronicrecordcard.data.Versionable;
import diploma.electronicrecordcard.exception.versionconflict.VersionConflictException;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import static diploma.electronicrecordcard.data.constant.FieldConstants.VERSION;

public class VersionUtil {

    private VersionUtil() {
        throw new AssertionError("Cannot be instantiated");
    }

    public static boolean checkVersion(Versionable oldObject, Versionable newObject) {
        return Objects.equals(oldObject.getVersion(), newObject.getVersion());
    }

    public static void checkVersionAndThrowVersionConflict(Versionable oldObject,
                                                           Versionable newObject,
                                                           Class<? extends VersionConflictException> exceptionClass) {
        if(!checkVersion(oldObject, newObject)) {
            try {
                throw exceptionClass.getConstructor(Long.class).newInstance(newObject.getVersion());
            } catch (InstantiationException
                     | IllegalAccessException
                     | InvocationTargetException
                     | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static <T> Specification<T> getVersionSpecification(Long version) {
        return ((root, _, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(VERSION), version));
    }
}
