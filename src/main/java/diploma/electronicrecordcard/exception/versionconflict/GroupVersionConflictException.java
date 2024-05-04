package diploma.electronicrecordcard.exception.versionconflict;

import static diploma.electronicrecordcard.data.constant.EntityNameConstants.GROUP;

public class GroupVersionConflictException extends VersionConflictException {

    public GroupVersionConflictException(Long version) {
        super(GROUP, version);
    }
}
