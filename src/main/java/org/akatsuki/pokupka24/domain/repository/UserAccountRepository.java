package org.akatsuki.pokupka24.domain.repository;

import org.akatsuki.pokupka24.domain.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {

//    @Query("select ua from UserAccount ua where ua.user.userId = ?1")
    UserAccount findAccountByUser_UserId(UUID userId);

}
