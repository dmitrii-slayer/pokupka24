package org.akatsuki.pokupka24.service;

import lombok.RequiredArgsConstructor;
import org.akatsuki.pokupka24.domain.entity.User;
import org.akatsuki.pokupka24.domain.entity.UserAccount;
import org.akatsuki.pokupka24.domain.repository.UserAccountRepository;
import org.akatsuki.pokupka24.domain.repository.UserRepository;
import org.akatsuki.pokupka24.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserAccountRepository userAccountRepository;

    @Override
    public Page<User> findUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("No user with ID: " + userId));
    }

    @Transactional
    @Override
    public User addUser(User user) {
        user.setRegistrationDate(LocalDate.now());
        User savedUser = userRepository.save(user);
        UserAccount userAccount = addUserAccount(savedUser);
        savedUser.setUserAccount(userAccount);
        return savedUser;
    }

    @Override
    public User updateUser(UUID userId, User user) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new NotFoundException("No user with ID: " + userId);
        }
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public UserAccount findUserAccount(UUID userId) {
        return userAccountRepository.findAccountByUser_UserId(userId);
    }

    @Override
    public UserAccount addUserAccount(User user) {
        UserAccount account = new UserAccount();
        account.setUser(user);
        account.setIsActive(true);
        // нужен setScale? и нужно ли вообще 0 ставить
        account.setBalance(BigDecimal.ZERO.setScale(2, RoundingMode.UNNECESSARY));
        return userAccountRepository.save(account);
    }

    @Override
    public UserAccount updateUserAccount(UUID userId, UserAccount account) {
        if (findUserAccount(userId) == null) {
            throw new NotFoundException("User " + userId + " does not have an account.");
        }
        account.getUser().setUserId(userId);
        return userAccountRepository.save(account);
    }

//    Lock ??
    @Override
    public UserAccount addFunds(UUID accountId, BigDecimal addAmount) {
        UserAccount account = userAccountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("No account with ID: " + accountId));
        BigDecimal newBalance = account.getBalance().add(addAmount);
        account.setBalance(newBalance);
        return userAccountRepository.save(account);
    }
}
