package diploma.electronicrecordcard.exception.entitynotfound;

import static diploma.electronicrecordcard.data.constant.EntityNameConstants.SUBJECT;

public class SubjectNotFoundException extends EntityNotFoundException{

    private static final String DEFAULT_FIELD_NAME = "id";

    public SubjectNotFoundException(String value) {
        super(SUBJECT, DEFAULT_FIELD_NAME, value);
    }

    public SubjectNotFoundException(String fieldName, String value) {
        super(SUBJECT, fieldName, value);
    }

}
