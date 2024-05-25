package org.akatsuki.pokupka24.service;

import org.akatsuki.pokupka24.domain.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> findAllUsers();

    User findUserById(UUID id);

    User addUser(User user);

}
