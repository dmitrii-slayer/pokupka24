package org.akatsuki.pokupka24.handler.exception;

import java.util.UUID;

public class NoSuchProductException extends RuntimeException {

    public NoSuchProductException(UUID id) {
        super("No product with ID = " + id);
    }
}
