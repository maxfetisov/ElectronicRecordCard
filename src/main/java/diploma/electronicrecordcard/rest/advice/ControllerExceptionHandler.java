package diploma.electronicrecordcard.rest.advice;

import diploma.electronicrecordcard.data.dto.error.ErrorMessageDto;
import diploma.electronicrecordcard.data.enumeration.ErrorType;
import diploma.electronicrecordcard.exception.entitynotfound.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorMessageDto.builder()
                        .type(ErrorType.ENTITY_NOT_FOUND)
                        .message(e.getMessage())
                        .build());
    }

}
