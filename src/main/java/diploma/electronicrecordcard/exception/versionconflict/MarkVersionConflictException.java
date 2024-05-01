package diploma.electronicrecordcard.exception.versionconflict;

public class MarkVersionConflictException extends VersionConflictException {

    private static final String ENTITY_NAME = "Оценка";

    public MarkVersionConflictException(Long version) {
        super(ENTITY_NAME, version);
    }

}
