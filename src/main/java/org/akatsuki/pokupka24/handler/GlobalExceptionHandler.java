package org.akatsuki.pokupka24.handler;

import lombok.extern.slf4j.Slf4j;
import org.akatsuki.pokupka24.handler.exception.NoSuchProductException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler
//    public ResponseEntity<ErrorResponse> handleException(NoSuchUserException exception) {
//        log.debug(exception.getMessage());
//        ErrorResponse data = new ErrorResponse();
//        data.setMessage(exception.getMessage());
//        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler
//    public ResponseEntity<ErrorResponse> handleException(ConstraintViolationException exception) {
//        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
//        log.error(violations.toString());
//        ErrorResponse data = new ErrorResponse();
//        data.setMessage(exception.getLocalizedMessage());
//        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler
//    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException exception) {
//        log.error(exception.getMessage());
//        ErrorResponse data = new ErrorResponse();
//        data.setMessage(exception.getMessage());
//        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
//    }
//
//    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
//    @ExceptionHandler
//    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
//        log.error(exception.getCause().getMessage());
//        ErrorResponse data = new ErrorResponse();
//        data.setMessage(exception.getMessage());
//        return new ResponseEntity<>(data, HttpStatus.I_AM_A_TEAPOT);
//    }


//    @ExceptionHandler(NoSuchProductException.class)
//    public ResponseEntity<ErrorResponse> handleException(NoSuchProductException ex) {
//        log.error("Exception: ", ex);
//        ErrorResponse response = new ErrorResponse();
//        response.setMessage(ex.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//    }

//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NoSuchProductException.class)
//    public ErrorResponse handleException(NoSuchProductException ex) {
//        log.error("Exception: ", ex);
//        ErrorResponse response = new ErrorResponse();
//        response.setMessage(ex.getMessage());
//        return response;
//    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ErrorResponse> handleException(NoSuchProductException ex) {
        log.error("Exception: ", ex);
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ErrorResponse> handleException(ResponseStatusException ex) {
        log.error("Exception: ", ex);
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(response);
    }


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        log.error("GlobalExceptionHandler: ", ex);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
