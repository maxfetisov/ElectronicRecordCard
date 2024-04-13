package diploma.electronicrecordcard.data.dto.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
public record UserDto (
        Long id,
        String login,
        String lastName,
        String firstName,
        String middleName,
        String phoneNumber,
        String email,
        String recordBookNumber,
        Boolean deleted,
        Integer groupId,
        Short instituteId
) {
}
