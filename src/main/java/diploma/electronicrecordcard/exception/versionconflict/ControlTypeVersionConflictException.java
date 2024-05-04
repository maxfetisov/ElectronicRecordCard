package diploma.electronicrecordcard.exception.versionconflict;

import static diploma.electronicrecordcard.data.constant.EntityNameConstants.CONTROL_TYPE;

public class ControlTypeVersionConflictException extends VersionConflictException {

    public ControlTypeVersionConflictException(Long version) {
        super(CONTROL_TYPE, version);
    }

}
