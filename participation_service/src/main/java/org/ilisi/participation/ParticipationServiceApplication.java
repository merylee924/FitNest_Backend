package org.ilisi.participation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories
public class ParticipationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParticipationServiceApplication.class, args);
	}


}
