package diploma.electronicrecordcard.exception.versionconflict;

public class VersionConflictException extends RuntimeException {

    private static final String DEFAULT_MESSAGE_PATTERN = "Неатуальная версия %d записи сущности '%s'";

    public VersionConflictException(String entityName, Long version) {
        super(String.format(DEFAULT_MESSAGE_PATTERN, version, entityName));
    }


}
