package diploma.electronicrecordcard.exception.entitynotfound;

import static diploma.electronicrecordcard.data.constant.EntityNameConstants.GROUP;

public class GroupNotFoundException extends EntityNotFoundException {

    private static final String DEFAULT_FIELD_NAME = "id";

    public GroupNotFoundException(String value) {
        super(GROUP, DEFAULT_FIELD_NAME, value);
    }

    public GroupNotFoundException(String fieldName, String value) {
        super(GROUP, fieldName, value);
    }


}
