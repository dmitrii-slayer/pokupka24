package org.akatsuki.pokupka24.service;

import org.akatsuki.pokupka24.domain.entity.User;
import org.akatsuki.pokupka24.domain.entity.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.UUID;

public interface UserService {

    Page<User> findUsers(Pageable pageable);

    User findUserById(UUID userId);

    User addUser(User user);

    User updateUser(UUID userId, User user);

    UserAccount findUserAccount(UUID userId);

    UserAccount addUserAccount(UUID userId, UserAccount account);

    UserAccount updateUserAccount(UUID userId, UserAccount account);

    UserAccount addFunds(UUID accountId, BigDecimal addAmount);

}
