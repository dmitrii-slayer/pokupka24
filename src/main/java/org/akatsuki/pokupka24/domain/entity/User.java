package org.akatsuki.pokupka24.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users") // users так как user зарезервированное слово
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @NotBlank
    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "birth_date")
    private LocalDate birthDate;

//    @OneToOne // one-to-many lazy по-умолчанию
//    @JoinColumn(name = "account_id")
////    @Column(name = "account_id")
//    private UserAccount userAccounts;

}