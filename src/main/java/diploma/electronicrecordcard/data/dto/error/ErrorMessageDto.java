package diploma.electronicrecordcard.data.dto.error;

import diploma.electronicrecordcard.data.enumeration.ErrorType;
import lombok.Builder;

@Builder
public record ErrorMessageDto (ErrorType type, String message) {
}
