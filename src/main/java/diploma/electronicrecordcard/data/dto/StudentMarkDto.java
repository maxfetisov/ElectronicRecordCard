package diploma.electronicrecordcard.data.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentMarkDto {

    Long id;

    Short semester;

    LocalDate completionDate;

    Short hoursNumber;

    Long teacherId;

    Long studentId;

    Long subjectId;

    Short controlTypeId;

    Short markId;

}
