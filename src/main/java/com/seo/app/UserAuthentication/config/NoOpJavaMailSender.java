package com.seo.app.UserAuthentication.config;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;

/**
 * Swallows outbound mail (used with {@code h2} profile for local API runs without SMTP).
 */
public class NoOpJavaMailSender extends JavaMailSenderImpl {

    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {
        // no-op
    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {
        // no-op
    }

    @Override
    public void send(MimeMessage mimeMessage) throws MailException {
        // no-op
    }

    @Override
    public void send(MimeMessage... mimeMessages) throws MailException {
        // no-op
    }

    @Override
    public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {
        // no-op
    }

    @Override
    public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {
        // no-op
    }
}
