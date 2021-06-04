package com.kbpark9898.BoooardGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BoooardGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoooardGameApplication.class, args);
	}

}
