package diploma.electronicrecordcard.exception.entitynotfound;

public class SubjectNotFoundException extends EntityNotFoundException{

    private static final String ENTITY_NAME = "Предмет";

    private static final String DEFAULT_FIELD_NAME = "id";

    public SubjectNotFoundException(String value) {
        super(ENTITY_NAME, DEFAULT_FIELD_NAME, value);
    }

    public SubjectNotFoundException(String fieldName, String value) {
        super(ENTITY_NAME, fieldName, value);
    }

}
