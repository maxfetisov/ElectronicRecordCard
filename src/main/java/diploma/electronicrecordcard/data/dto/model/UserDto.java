package diploma.electronicrecordcard.data.dto.model;

import lombok.Builder;

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
