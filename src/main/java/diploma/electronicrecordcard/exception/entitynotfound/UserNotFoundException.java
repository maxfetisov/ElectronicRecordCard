package diploma.electronicrecordcard.exception.entitynotfound;

public class UserNotFoundException extends EntityNotFoundException {

    private static final String ENTITY_NAME = "Пользователь";

    private static final String DEFAULT_FIELD_NAME = "id";

    public UserNotFoundException(String value) {
        super(ENTITY_NAME, DEFAULT_FIELD_NAME, value);
    }

    public UserNotFoundException(String fieldName, String value) {
        super(ENTITY_NAME, fieldName, value);
    }

}
