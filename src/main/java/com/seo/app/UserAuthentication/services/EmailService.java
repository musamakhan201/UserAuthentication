package com.seo.app.UserAuthentication.services;

import com.seo.app.UserAuthentication.domains.EmailTemplateDomain;
import com.seo.app.UserAuthentication.domains.UserRegistrationDomain;
import com.seo.app.UserAuthentication.repository.EmailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender javaMailSender;
    private final EmailRepository emailRepository;

    @Value("${spring.mail.username:}")
    private String mailFrom;

    public EmailService(JavaMailSender javaMailSender, EmailRepository emailRepository) {
        this.javaMailSender = javaMailSender;
        this.emailRepository = emailRepository;
    }

    public String sendMail(int templateId, String toAddress) {
        try {
            EmailTemplateDomain email = emailRepository.findByEmailTemplateID(templateId);
            if (email == null || email.getSubject() == null || email.getSubject().isEmpty()) {
                return "Failed to send email.";
            }
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject(email.getSubject());
            mailMessage.setFrom(mailFrom);
            mailMessage.setTo(toAddress);
            mailMessage.setText(email.getBody() != null ? email.getBody() : "");
            javaMailSender.send(mailMessage);
            return "Email has been sent successfully.";
        } catch (Exception e) {
            log.error("Failed to send email", e);
            return "Failed to send email.";
        }
    }

    public void sendRegistrationConfirmation(UserRegistrationDomain user, String confirmationToken) {
        EmailTemplateDomain email = emailRepository.findByEmailTemplateID(1);
        if (email == null) {
            log.warn("Registration email template (id=1) not found");
            return;
        }
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(email.getSubject());
        mailMessage.setFrom(mailFrom);
        mailMessage.setTo(user.getEmail());
        String body = email.getBody() != null ? email.getBody() : "";
        mailMessage.setText(body + confirmationToken);
        javaMailSender.send(mailMessage);
        log.info("Registration confirmation email queued for {}", user.getEmail());
    }
}
