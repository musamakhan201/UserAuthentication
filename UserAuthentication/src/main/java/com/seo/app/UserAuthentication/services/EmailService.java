package com.seo.app.UserAuthentication.services;

import com.seo.app.UserAuthentication.domains.ConfirmationTokenDomain;
import com.seo.app.UserAuthentication.domains.EmailTemplateDomain;
import com.seo.app.UserAuthentication.domains.UserRegistrationDomain;
import com.seo.app.UserAuthentication.repository.ConfirmationTokenRepository;
import com.seo.app.UserAuthentication.repository.EmailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    public String sendMail(int id,String mail) {
        try {
            EmailTemplateDomain email=emailRepository.findByEmailTemplateID(id);
            log.info(String.valueOf(email.getEmail_template_id()));
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            if (!(email.getSubject().isEmpty()))
            {
                    mailMessage.setSubject(email.getSubject());
                    mailMessage.setFrom("coretech2k20@gmail.com");
                    mailMessage.setTo(mail);
                    mailMessage.setText(email.getBody());
                    javaMailSender.send(mailMessage);
                    return "Email has been sent successfully.";
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "Failed to send email.";


    }
}
