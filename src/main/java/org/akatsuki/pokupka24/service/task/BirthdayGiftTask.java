package org.akatsuki.pokupka24.service.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BirthdayGiftTask {

    @Scheduled(cron = "${task.cron.birthday-present}", zone = "${timezone}")
    public void sendBirthdayPresent() {
        log.info("BirthdayGiftTask!!!");
    }
}
