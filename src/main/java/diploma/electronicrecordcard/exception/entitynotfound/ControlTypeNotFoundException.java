package diploma.electronicrecordcard.exception.entitynotfound;

public class ControlTypeNotFoundException extends EntityNotFoundException {

    private static final String ENTITY_NAME = "Тип контроля";

    private static final String DEFAULT_FIELD_NAME = "id";

    public ControlTypeNotFoundException(String value) {
        super(ENTITY_NAME, DEFAULT_FIELD_NAME, value);
    }

    public ControlTypeNotFoundException(String fieldName, String value) {
        super(ENTITY_NAME, fieldName, value);
    }
}
