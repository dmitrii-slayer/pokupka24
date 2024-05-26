package org.akatsuki.pokupka24.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserDTO {

    private UUID userId;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthDate;

}
