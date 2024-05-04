package diploma.electronicrecordcard.exception.versionconflict;

import static diploma.electronicrecordcard.data.constant.EntityNameConstants.SUBJECT;

public class SubjectVersionConflictException extends VersionConflictException {

    public SubjectVersionConflictException(Long version) {
        super(SUBJECT, version);
    }

}
