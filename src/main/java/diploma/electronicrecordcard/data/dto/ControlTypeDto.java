package diploma.electronicrecordcard.data.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ControlTypeDto {

    Short id;

    String name;

    String title;

}
