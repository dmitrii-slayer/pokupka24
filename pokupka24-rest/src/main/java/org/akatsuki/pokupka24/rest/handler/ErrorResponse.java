package org.akatsuki.pokupka24.rest.handler;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {

    private String message;

    private LocalDateTime timestamp = LocalDateTime.now();

}
