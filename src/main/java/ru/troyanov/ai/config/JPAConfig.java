package ru.troyanov.ai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProviderCustom")
@Configuration
public class JPAConfig {

    @Bean
    public DateTimeProvider dateTimeProviderCustom() {
        return () -> Optional.of(OffsetDateTime.now(ZoneOffset.UTC));
    }

}
