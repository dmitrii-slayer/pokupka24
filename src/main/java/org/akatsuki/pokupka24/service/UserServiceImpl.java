package org.akatsuki.pokupka24.service;

import lombok.AllArgsConstructor;
import org.akatsuki.pokupka24.domain.entity.User;
import org.akatsuki.pokupka24.domain.entity.UserAccount;
import org.akatsuki.pokupka24.domain.repository.UserAccountRepository;
import org.akatsuki.pokupka24.domain.repository.UserRepository;
import org.akatsuki.pokupka24.handler.exception.NoSuchAccountException;
import org.akatsuki.pokupka24.handler.exception.NoSuchUserException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserAccountRepository userAccountRepository;

    @Override
    public Page<User> findUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchUserException(userId));
    }

    @Override
    public User addUser(User user) {
        user.setRegistrationDate(LocalDate.now());
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
    public UserAccount findUserAccount(UUID userId) {
        return userAccountRepository.findAccountByUser_UserId(userId);
    }

    @Override
    public UserAccount addUserAccount(UUID userId, UserAccount account) {
        // не нужно так как есть FK constraint - обработать exception?
//        if (userRepository.findById(userId).isEmpty()) {
//            throw new NoSuchUserException(userId);
//        }
        account.setBalance(BigDecimal.ZERO.setScale(2, RoundingMode.UNNECESSARY)); // нужен setScale? и нужно ли вообще 0 ставить
        account.getUser().setUserId(userId);
        return userAccountRepository.save(account);
    }

    @Override
    public UserAccount updateUserAccount(UUID userId, UserAccount account) {
        if (findUserAccount(userId) == null) {
            throw new NoSuchAccountException("User " + userId + " does not have an account.");
        }
        account.getUser().setUserId(userId);
        return userAccountRepository.save(account);
    }

//    Lock ??
    @Override
    public UserAccount addFunds(UUID accountId, BigDecimal addAmount) {
        UserAccount account = userAccountRepository.findById(accountId)
                .orElseThrow(() -> new NoSuchAccountException(accountId));
        BigDecimal newBalance = account.getBalance().add(addAmount);
        account.setBalance(newBalance);
        return userAccountRepository.save(account);
    }
}
