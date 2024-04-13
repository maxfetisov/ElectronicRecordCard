package diploma.electronicrecordcard.exception.entitynotfound;

public class InstituteNotFoundException extends EntityNotFoundException {

    private static final String ENTITY_NAME = "Институт";

    private static final String DEFAULT_FIELD_NAME = "id";

    public InstituteNotFoundException(String value) {
        super(ENTITY_NAME, DEFAULT_FIELD_NAME, value);
    }

    public InstituteNotFoundException(String fieldName, String value) {
        super(ENTITY_NAME, fieldName, value);
    }

}
