package diploma.electronicrecordcard.exception.entityalreadyexists;

public class EntityAlreadyExistsException extends RuntimeException {

    private static final String DEFAULT_MESSAGE_PATTERN = " Сущность '%s' с %s=%s уже существует";

    public EntityAlreadyExistsException(String entityName, String fieldName, String value) {
        super(String.format(DEFAULT_MESSAGE_PATTERN, entityName, fieldName, value));
    }

}
