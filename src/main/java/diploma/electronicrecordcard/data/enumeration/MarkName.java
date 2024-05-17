package diploma.electronicrecordcard.data.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MarkName {

    NON_ADMISSION("Недопуск", (short) 0),
    FAILED("Незачет", (short) 2),
    PASSED("Зачет", (short) 5),
    UNSATISFACTORY("Неудовлетворительно", (short) 2),
    SATISFACTORY("Удовлетворительно", (short) 3),
    GOOD("Хорошо", (short) 4),
    EXCELLENT("Отлично", (short) 5),
    ABSENCE("Неявка", (short) 2),
    REASONABLE_ABSENCE("Неявка по уважительной причине", (short) 1),
    RELEASE("Освобождение", (short) 5);

    private final String title;

    private final short value;

}
