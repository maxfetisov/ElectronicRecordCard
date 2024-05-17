package diploma.electronicrecordcard.data.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ControlTypeName {

    CREDIT("Зачет"),
    DIF_CREDIT("Дифференцированный зачет"),
    EXAM("Экзамен"),
    COURSE_WORK("Курсовая работа"),
    COURSE_PROJECT("Курсовой проект");

    private final String title;

}
