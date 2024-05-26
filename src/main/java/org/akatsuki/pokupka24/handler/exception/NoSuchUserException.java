package org.akatsuki.pokupka24.handler.exception;

import java.util.UUID;

public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException(UUID userId) {
        super("No user with ID = " + userId);
    }

}
