package diploma.electronicrecordcard.exception.versionconflict;

import static diploma.electronicrecordcard.data.constant.EntityNameConstants.MARK;

public class MarkVersionConflictException extends VersionConflictException {

    public MarkVersionConflictException(Long version) {
        super(MARK, version);
    }

}
