package diploma.electronicrecordcard.exception.entityalreadyexists;

import static diploma.electronicrecordcard.data.constant.EntityNameConstants.USER;

public class UserAlreadyExistsException extends EntityAlreadyExistsException {


    private static final String DEFAULT_FIELD_NAME = "login";

    public UserAlreadyExistsException(String value) {
        super(USER, DEFAULT_FIELD_NAME, value);
    }

    public UserAlreadyExistsException(String fieldName, String value) {
        super(USER, fieldName, value);
    }

}
