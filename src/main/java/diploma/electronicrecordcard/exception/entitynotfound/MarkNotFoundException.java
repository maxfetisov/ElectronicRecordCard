package diploma.electronicrecordcard.exception.entitynotfound;

import static diploma.electronicrecordcard.data.constant.EntityNameConstants.MARK;

public class MarkNotFoundException extends EntityNotFoundException {

    private static final String DEFAULT_FIELD_NAME = "id";

    public MarkNotFoundException(String value) {
        super(MARK, DEFAULT_FIELD_NAME, value);
    }

    public MarkNotFoundException(String fieldName, String value) {
        super(MARK, fieldName, value);
    }

}
