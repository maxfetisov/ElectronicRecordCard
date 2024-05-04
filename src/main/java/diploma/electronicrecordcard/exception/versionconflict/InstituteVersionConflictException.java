package diploma.electronicrecordcard.exception.versionconflict;

import static diploma.electronicrecordcard.data.constant.EntityNameConstants.INSTITUTE;

public class InstituteVersionConflictException extends VersionConflictException {

    public InstituteVersionConflictException(Long version) {
        super(INSTITUTE, version);
    }


}
