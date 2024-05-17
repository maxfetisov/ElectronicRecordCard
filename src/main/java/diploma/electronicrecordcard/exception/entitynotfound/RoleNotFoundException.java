package diploma.electronicrecordcard.exception.entitynotfound;

import static diploma.electronicrecordcard.data.constant.EntityNameConstants.ROLE;

public class RoleNotFoundException extends EntityNotFoundException {

    private static final String DEFAULT_FIELD_NAME = "id";

    public RoleNotFoundException(String value) {
        super(ROLE, DEFAULT_FIELD_NAME, value);
    }

    public RoleNotFoundException(String fieldName, String value) {
        super(ROLE, fieldName, value);
    }

}
