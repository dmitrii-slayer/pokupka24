package org.akatsuki.pokupka24.domain.repository;

import org.akatsuki.pokupka24.domain.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {

    List<UserAccount> findAccountsByUserId(UUID userId);

}
