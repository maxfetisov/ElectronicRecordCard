package diploma.electronicrecordcard.util;

import diploma.electronicrecordcard.data.Versionable;
import diploma.electronicrecordcard.exception.versionconflict.VersionConflictException;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

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
}
