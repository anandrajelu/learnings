package io.anand.raj.springh2db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("io.anand.raj.springh2db.repository")
@EntityScan("io.anand.raj.springh2db.*")
public class SpringH2DbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringH2DbApplication.class, args);
	}

}
