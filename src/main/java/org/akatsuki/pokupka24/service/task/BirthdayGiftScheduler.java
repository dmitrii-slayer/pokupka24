package org.akatsuki.pokupka24.service.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.akatsuki.pokupka24.domain.entity.User;
import org.akatsuki.pokupka24.domain.repository.UserRepository;
import org.akatsuki.pokupka24.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BirthdayGiftScheduler {

    // возможно не оч корректно использовать репозиторий напрямую? хотя вроде норм
    private final UserRepository userRepository;
    private final UserService userService;

    @Value("${task.birthday-gift.amount}")
    private BigDecimal birthdayGiftAmount;

    // @Async ?
    @Scheduled(cron = "${task.birthday-gift.cron}", zone = "${timezone}")
    public void sendBirthdayGift() {
        log.info("BirthdayGiftTask!!!");
//        log.info("Birthday gift amount: {}", birthdayPresentAmount.toString());
        LocalDate localDate = LocalDate.now();
        // по идее только id account-ов нужны а не полноценные User + UserAccount
        List<User> birthdayUsers = userRepository.findByBirthday(localDate.getDayOfMonth(), localDate.getMonthValue());
//        log.info("Birthday users: {}", birthdayUsers);
        // добавить фильтрацию пользователей по дате регистрации?
        // (дарить деньги только тем кто зарегистрировался > 2 лет назад)
        for (User user : birthdayUsers) {
            userService.addFunds(user.getUserAccount().getAccountId(), birthdayGiftAmount);
        }
    }
}
