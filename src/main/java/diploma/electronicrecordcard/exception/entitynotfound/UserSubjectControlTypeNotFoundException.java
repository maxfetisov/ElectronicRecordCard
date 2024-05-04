package diploma.electronicrecordcard.exception.entitynotfound;

import static diploma.electronicrecordcard.data.constant.EntityNameConstants.USER_SUBJECT_CONTROL_TYPE;

public class UserSubjectControlTypeNotFoundException extends EntityNotFoundException {

    private static final String DEFAULT_FIELD_NAME = "id";

    public UserSubjectControlTypeNotFoundException(String value) {
        super(USER_SUBJECT_CONTROL_TYPE, DEFAULT_FIELD_NAME, value);
    }

    public UserSubjectControlTypeNotFoundException(String fieldName, String value) {
        super(USER_SUBJECT_CONTROL_TYPE, fieldName, value);
    }


}
