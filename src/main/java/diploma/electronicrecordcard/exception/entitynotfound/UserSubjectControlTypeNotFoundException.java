package diploma.electronicrecordcard.exception.entitynotfound;

public class UserSubjectControlTypeNotFoundException extends EntityNotFoundException {

    private static final String ENTITY_NAME = "Пользователь - Предмет - Тип контроля";

    private static final String DEFAULT_FIELD_NAME = "id";

    public UserSubjectControlTypeNotFoundException(String value) {
        super(ENTITY_NAME, DEFAULT_FIELD_NAME, value);
    }

    public UserSubjectControlTypeNotFoundException(String fieldName, String value) {
        super(ENTITY_NAME, fieldName, value);
    }


}
