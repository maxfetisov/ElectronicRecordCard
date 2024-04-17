package diploma.electronicrecordcard.exception.entitynotfound;

public class MarkNotFoundException extends EntityNotFoundException {

    private static final String ENTITY_NAME = "Оценка";

    private static final String DEFAULT_FIELD_NAME = "id";

    public MarkNotFoundException(String value) {
        super(ENTITY_NAME, DEFAULT_FIELD_NAME, value);
    }

    public MarkNotFoundException(String fieldName, String value) {
        super(ENTITY_NAME, fieldName, value);
    }

}
