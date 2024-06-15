package org.akatsuki.pokupka24.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@ToString
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "registration_date", nullable = false, updatable = false)
    private LocalDate registrationDate;

    @OneToOne(mappedBy = "user")
    private UserAccount userAccount;

}