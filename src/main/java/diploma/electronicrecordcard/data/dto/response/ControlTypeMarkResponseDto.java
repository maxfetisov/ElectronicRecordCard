package diploma.electronicrecordcard.data.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record ControlTypeMarkResponseDto (

        Short controlTypeId,

        List<Short> markIds

) {
}
