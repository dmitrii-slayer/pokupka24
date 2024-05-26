package org.akatsuki.pokupka24.service;

import org.akatsuki.pokupka24.domain.entity.User;
import org.akatsuki.pokupka24.domain.entity.UserAccount;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> findAllUsers();

    User findUserById(UUID userId);

    User addUser(User user);

    User updateUser(UUID userId, User user);

    List<UserAccount> findUserAccounts(UUID userId);

}
