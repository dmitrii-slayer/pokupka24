package org.akatsuki.pokupka24.handler;

import lombok.extern.slf4j.Slf4j;
import org.akatsuki.pokupka24.handler.exception.NoSuchUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class UserGlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handleException(NoSuchUserException exception) {
        log.debug(exception.getMessage());
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handleException(Exception exception) {
        log.error(exception.getMessage());
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.I_AM_A_TEAPOT);
    }

}
