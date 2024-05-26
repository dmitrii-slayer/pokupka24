package org.akatsuki.pokupka24.service;

import org.akatsuki.pokupka24.domain.entity.User;
import org.akatsuki.pokupka24.domain.entity.UserAccount;
import org.akatsuki.pokupka24.domain.repository.UserAccountRepository;
import org.akatsuki.pokupka24.domain.repository.UserRepository;
import org.akatsuki.pokupka24.handler.exception.NoSuchUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchUserException(userId));
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UUID userId, User user) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new NoSuchUserException(userId);
        }
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<UserAccount> findUserAccounts(UUID userId) {
        return userAccountRepository.findAccountsByUserId(userId);
    }
}
