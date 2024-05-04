package diploma.electronicrecordcard.exception.versionconflict;

public class ControlTypeVersionConflict extends VersionConflictException {

    private static final String ENTITY_NAME = "Тип контроля";

    public ControlTypeVersionConflict(Long version) {
        super(ENTITY_NAME, version);
    }

}
