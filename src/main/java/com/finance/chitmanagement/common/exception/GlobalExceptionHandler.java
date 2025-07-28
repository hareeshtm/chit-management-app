package com.finance.chitmanagement.common.exception;

import com.finance.chitmanagement.common.dto.ChitErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ChitErrorResponse> handleRuntimeException(RuntimeException exception){
        ChitErrorResponse errors = new ChitErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ChitErrorResponse> handleAccessDeniedException(AccessDeniedException exception) {
        ChitErrorResponse errors = new ChitErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                "Access Denied: You do not have permission to access this resource.",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ChitErrorResponse> handleGeneralException(Exception exception){
        ChitErrorResponse errors = new ChitErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
