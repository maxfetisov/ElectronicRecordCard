package diploma.electronicrecordcard.exception.entitynotfound;

import static diploma.electronicrecordcard.data.constant.EntityNameConstants.STUDENT_MARK;

public class StudentMarkNotFoundException extends EntityNotFoundException {

    private static final String DEFAULT_FIELD_NAME = "id";

    public StudentMarkNotFoundException(String value) {
        super(STUDENT_MARK, DEFAULT_FIELD_NAME, value);
    }

    public StudentMarkNotFoundException(String fieldName, String value) {
        super(STUDENT_MARK, fieldName, value);
    }

}
