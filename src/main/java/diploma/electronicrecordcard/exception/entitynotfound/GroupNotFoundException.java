package diploma.electronicrecordcard.exception.entitynotfound;

public class GroupNotFoundException extends EntityNotFoundException {

    private static final String ENTITY_NAME = "Группа";

    private static final String DEFAULT_FIELD_NAME = "id";

    public GroupNotFoundException(String value) {
        super(ENTITY_NAME, DEFAULT_FIELD_NAME, value);
    }

    public GroupNotFoundException(String fieldName, String value) {
        super(ENTITY_NAME, fieldName, value);
    }


}
