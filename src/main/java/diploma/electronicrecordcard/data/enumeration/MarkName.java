package diploma.electronicrecordcard.data.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MarkName {

    NON_ADMISSION("Недопуск", (short) 1),
    FAIL("Незачет", (short) 2),
    SUCCESS("Зачет", (short) 5),
    UNSATISFACTORY("Неудовлетворительно", (short) 2),
    SATISFACTORY("Удовлетворительно", (short) 3),
    WELL("Хорошо", (short) 4),
    GREAT("Отлично", (short) 5);

    private final String title;

    private final short value;

}
