package se.madev.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import se.madev.main.integration.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"se.madev.main.integration"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
