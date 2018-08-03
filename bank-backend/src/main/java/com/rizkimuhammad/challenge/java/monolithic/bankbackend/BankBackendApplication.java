package com.rizkimuhammad.challenge.java.monolithic.bankbackend;

import com.rizkimuhammad.challenge.java.monolithic.bankbackend.util.AuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BankBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankBackendApplication.class, args);
	}

    @Bean
    public AuditorAware<String> getAuditorAware() {
        return new AuditorAwareImpl();
    }
}
