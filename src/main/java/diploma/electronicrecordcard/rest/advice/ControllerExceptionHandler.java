package diploma.electronicrecordcard.rest.advice;

import diploma.electronicrecordcard.data.dto.error.ErrorMessageDto;
import diploma.electronicrecordcard.data.enumeration.ErrorType;
import diploma.electronicrecordcard.exception.entityalreadyexists.EntityAlreadyExistsException;
import diploma.electronicrecordcard.exception.entitynotfound.EntityNotFoundException;
import diploma.electronicrecordcard.exception.illegalvalue.IllegalValueException;
import diploma.electronicrecordcard.exception.noauthority.NoAuthorityException;
import diploma.electronicrecordcard.exception.versionconflict.VersionConflictException;
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

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ErrorMessageDto> handleEntityAlreadyExistsException(EntityAlreadyExistsException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessageDto.builder()
                        .type(ErrorType.ENTITY_ALREADY_EXISTS)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(VersionConflictException.class)
    public ResponseEntity<ErrorMessageDto> handleVersionConflictException(VersionConflictException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorMessageDto.builder()
                        .type(ErrorType.VERSION_CONFLICT)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(NoAuthorityException.class)
    public ResponseEntity<ErrorMessageDto> handleNoAuthorityException(NoAuthorityException e) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ErrorMessageDto.builder()
                        .type(ErrorType.NO_AUTHORITY)
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(IllegalValueException.class)
    public ResponseEntity<ErrorMessageDto> handleIllegalValueException(IllegalValueException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessageDto.builder()
                        .type(ErrorType.ILLEGAL_VALUE)
                        .message(e.getMessage())
                        .build());
    }

}
