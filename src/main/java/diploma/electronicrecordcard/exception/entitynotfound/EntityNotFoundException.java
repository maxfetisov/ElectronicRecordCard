package diploma.electronicrecordcard.exception.entitynotfound;

public class EntityNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE_PATTERN = "Не удалось найти сущность '%s' с %s=%s";

    public EntityNotFoundException(String entityName, String fieldName, String value) {
        super(String.format(DEFAULT_MESSAGE_PATTERN, entityName, fieldName, value));
    }

}
