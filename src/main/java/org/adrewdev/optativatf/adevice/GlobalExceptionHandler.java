package org.adrewdev.optativatf.adevice;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request) {
        var errorMessage = ex.getMessage();
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .errorCode("GENERAL_ERROR")
                        .errorMessage(errorMessage)
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeExceptions(RuntimeException ex, WebRequest request) {
        var errorMessage = ex.getMessage();
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .errorCode("RUNTIME_ERROR")
                        .errorMessage(errorMessage)
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}

@Data
@Builder
class ErrorResponse {
    private String errorCode;
    private String errorMessage;
}
