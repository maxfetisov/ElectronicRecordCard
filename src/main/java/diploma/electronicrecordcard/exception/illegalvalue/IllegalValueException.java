package diploma.electronicrecordcard.exception.illegalvalue;

public class IllegalValueException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Некорректное значение поля %s = %s";

    public IllegalValueException(String fieldName, String value) {
        super(DEFAULT_MESSAGE.formatted(fieldName, value));
    }

}
