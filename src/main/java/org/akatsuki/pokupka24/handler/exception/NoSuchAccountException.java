package org.akatsuki.pokupka24.handler.exception;

import java.util.UUID;

public class NoSuchAccountException extends RuntimeException {

    public NoSuchAccountException(UUID id) {
        super("No account with ID = " + id);
    }

    public NoSuchAccountException(String message) {
        super(message);
    }
}
