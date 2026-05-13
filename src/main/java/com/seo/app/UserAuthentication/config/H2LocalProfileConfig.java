package com.seo.app.UserAuthentication.config;

import com.seo.app.UserAuthentication.domains.EmailTemplateDomain;
import com.seo.app.UserAuthentication.repository.EmailRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
@Profile("h2")
public class H2LocalProfileConfig {

    @Bean
    @Primary
    public JavaMailSender javaMailSender() {
        return new NoOpJavaMailSender();
    }

    /**
     * Seed email templates so registration / password-change / update flows behave like production.
     */
    @Bean
    @Order(0)
    public ApplicationRunner h2EmailTemplateSeed(EmailRepository emailRepository) {
        return args -> {
            if (emailRepository.count() > 0) {
                return;
            }
            emailRepository.save(new EmailTemplateDomain(0, "Confirm registration", "Open link with token: "));
            emailRepository.save(new EmailTemplateDomain(0, "Password changed", "Your password was updated."));
            emailRepository.save(new EmailTemplateDomain(0, "Profile updated", "Your profile was updated."));
        };
    }
}
