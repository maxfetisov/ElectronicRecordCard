package diploma.electronicrecordcard.exception.entitynotfound;

import static diploma.electronicrecordcard.data.constant.EntityNameConstants.USER;

public class UserNotFoundException extends EntityNotFoundException {

    private static final String DEFAULT_FIELD_NAME = "id";

    public UserNotFoundException(String value) {
        super(USER, DEFAULT_FIELD_NAME, value);
    }

    public UserNotFoundException(String fieldName, String value) {
        super(USER, fieldName, value);
    }

}
