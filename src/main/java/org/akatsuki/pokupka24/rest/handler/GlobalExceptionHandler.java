package org.akatsuki.pokupka24.rest.handler;

import lombok.extern.slf4j.Slf4j;
import org.akatsuki.pokupka24.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NoSuchProductException.class)
//    public ErrorResponse handleException(NoSuchProductException ex) {
//        log.error("Exception: ", ex);
//        ErrorResponse response = new ErrorResponse();
//        response.setMessage(ex.getMessage());
//        return response;
//    }

//    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(NotFoundException ex) {
        log.error(ex.getMessage());
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

//    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error(ex.getMessage());
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(response);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error(ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
