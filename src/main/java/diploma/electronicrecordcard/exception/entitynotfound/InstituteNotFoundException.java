package diploma.electronicrecordcard.exception.entitynotfound;

import static diploma.electronicrecordcard.data.constant.EntityNameConstants.INSTITUTE;

public class InstituteNotFoundException extends EntityNotFoundException {

    private static final String DEFAULT_FIELD_NAME = "id";

    public InstituteNotFoundException(String value) {
        super(INSTITUTE, DEFAULT_FIELD_NAME, value);
    }

    public InstituteNotFoundException(String fieldName, String value) {
        super(INSTITUTE, fieldName, value);
    }

}
