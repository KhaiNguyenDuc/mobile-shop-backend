package com.mobile.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.mobile.backend.security.AuditAwareImpl;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {

	@Bean
	AuditorAware<Long> auditorProvider() {
		return new AuditAwareImpl();
	}
	
	
}
