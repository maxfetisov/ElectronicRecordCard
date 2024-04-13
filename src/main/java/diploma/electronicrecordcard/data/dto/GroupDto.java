package diploma.electronicrecordcard.data.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupDto {

    Integer id;

    String name;

    String fullName;

    LocalDate admissionDate;

    Boolean deleted;

    Short instituteId;

}
