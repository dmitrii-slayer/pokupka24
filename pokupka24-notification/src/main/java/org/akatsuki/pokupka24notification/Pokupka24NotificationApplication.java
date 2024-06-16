package org.akatsuki.pokupka24notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Pokupka24NotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(Pokupka24NotificationApplication.class, args);
	}

}
