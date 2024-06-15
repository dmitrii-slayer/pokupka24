package org.akatsuki.pokupka24;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Pokupka24Application {

	public static void main(String[] args) {
		SpringApplication.run(Pokupka24Application.class, args);
	}

}
