package diploma.electronicrecordcard.exception.entitynotfound;

import static diploma.electronicrecordcard.data.constant.EntityNameConstants.CONTROL_TYPE;

public class ControlTypeNotFoundException extends EntityNotFoundException {

    private static final String DEFAULT_FIELD_NAME = "id";

    public ControlTypeNotFoundException(String value) {
        super(CONTROL_TYPE, DEFAULT_FIELD_NAME, value);
    }

    public ControlTypeNotFoundException(String fieldName, String value) {
        super(CONTROL_TYPE, fieldName, value);
    }
}
