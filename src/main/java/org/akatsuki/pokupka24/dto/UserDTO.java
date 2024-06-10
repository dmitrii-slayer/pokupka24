package org.akatsuki.pokupka24.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserDTO {

    @Schema(example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID userId;

    @Size(min = 4, max = 30)
    @Schema(example = "noobslayer")
    private String username;

    @Size(max = 50)
    @Schema(example = "Валентин")
    private String firstName;

    @Size(max = 50)
    @Schema(example = "Сидоров")
    private String lastName;

    @Email
    @Schema(example = "john.doe@test.com")
    private String email;

    @Past
    @Schema(example = "1990-05-26")
    private LocalDate birthDate;

    @Schema(example = "2024-06-10")
    private LocalDate registrationDate;

}
