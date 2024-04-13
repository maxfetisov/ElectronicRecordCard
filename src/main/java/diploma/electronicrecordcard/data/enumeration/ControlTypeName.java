package diploma.electronicrecordcard.data.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ControlTypeName {

    CREDIT("Зачет"),
    DIFFERENTIATED_CREDIT("Дифференцированный зачет"),
    EXAM("Экзамен"),
    RECERTIFICATION("Переаттестация"),
    EDUCATIONAL_PRACTICE("Учебная практика"),
    PRODUCTION_PRACTICE("Производственная практика"),
    PRE_GRADUATE_PRACTICE("Преддипломная практика"),
    COURSE_PAPER("Курсовая работа");

    private final String title;

}
