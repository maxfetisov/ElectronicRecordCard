package diploma.electronicrecordcard.exception.entityalreadyexists;

public class UserAlreadyExistsException extends EntityAlreadyExistsException {

    private static final String ENTITY_NAME = "Пользователь";

    private static final String DEFAULT_FIELD_NAME = "login";

    public UserAlreadyExistsException(String value) {
        super(ENTITY_NAME, DEFAULT_FIELD_NAME, value);
    }

    public UserAlreadyExistsException(String fieldName, String value) {
        super(ENTITY_NAME, fieldName, value);
    }

}
