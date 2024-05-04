package diploma.electronicrecordcard.exception.versionconflict;

import static diploma.electronicrecordcard.data.constant.EntityNameConstants.STUDENT_MARK;

public class StudentMarkVersionConflictException extends VersionConflictException {

    public StudentMarkVersionConflictException(Long version) {
        super(STUDENT_MARK, version);
    }

}
