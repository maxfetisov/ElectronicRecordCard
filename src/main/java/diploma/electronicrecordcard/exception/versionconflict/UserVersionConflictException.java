package diploma.electronicrecordcard.exception.versionconflict;

import static diploma.electronicrecordcard.data.constant.EntityNameConstants.USER;

public class UserVersionConflictException extends VersionConflictException {

    public UserVersionConflictException(Long version) {
        super(USER, version);
    }

}
