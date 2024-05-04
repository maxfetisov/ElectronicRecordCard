package diploma.electronicrecordcard.exception.versionconflict;

import static diploma.electronicrecordcard.data.constant.EntityNameConstants.USER_SUBJECT_CONTROL_TYPE;

public class UserSubjectControlTypeVersionConflictException extends VersionConflictException {

    public UserSubjectControlTypeVersionConflictException(Long version) {
        super(USER_SUBJECT_CONTROL_TYPE, version);
    }

}
