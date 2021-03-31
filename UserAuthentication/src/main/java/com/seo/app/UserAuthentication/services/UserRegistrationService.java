package com.seo.app.UserAuthentication.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seo.app.UserAuthentication.domain.transfer.object.UserRegistrationDto;
import com.seo.app.UserAuthentication.domains.ConfirmationTokenDomain;
import com.seo.app.UserAuthentication.domains.EmailTemplateDomain;
import com.seo.app.UserAuthentication.domains.UserRegistrationDomain;
import com.seo.app.UserAuthentication.repository.ConfirmationTokenRepository;
import com.seo.app.UserAuthentication.repository.EmailRepository;
import com.seo.app.UserAuthentication.repository.UserRegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {
    private final Logger log = LoggerFactory.getLogger(UserRegistrationService.class);

    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private EmailService emailSenderService;

    @Autowired
    private EmailRepository emailRepository;

    public String registerUser(UserRegistrationDto userRegistrationDto)
    {
        UserRegistrationDomain userRegistrationDomain;
        UserRegistrationDomain user = userRegistrationRepository.findByEmail(userRegistrationDto.getEmail());
        EmailTemplateDomain email=emailRepository.findByEmailTemplateID(1);
        if (user!=null)
        {
            if (user.getEmail().equals(userRegistrationDto.getEmail()))
            {
                String responseMessage = "User Already Exists With This Email";
                log.info(responseMessage);
                return responseMessage;
            }
            else if (user.getUsername().equals(userRegistrationDto.getUsername()))
            {
                String responseMessage = "User Already Exists With This Username";
                log.info(responseMessage);
                return responseMessage;
            }
            else{
                userRegistrationDomain=objectMapper.convertValue(userRegistrationDto,UserRegistrationDomain.class);
                userRegistrationRepository.save(userRegistrationDomain);
                ConfirmationTokenDomain confirmationToken = new ConfirmationTokenDomain(userRegistrationDomain);
                confirmationTokenRepository.save(confirmationToken);
                SimpleMailMessage mailMessage=new SimpleMailMessage();
                log.info(confirmationToken.getConfirmationToken());
                mailMessage.setSubject(email.getSubject());
                mailMessage.setFrom("coretech2k20@gmail.com");
                mailMessage.setTo(userRegistrationDomain.getEmail());
                mailMessage.setText(email.getBody()+confirmationToken.getConfirmationToken());
                javaMailSender.send(mailMessage);
                return "Email has been sent successfully.";
            }
        }
        else{
            userRegistrationDomain=objectMapper.convertValue(userRegistrationDto,UserRegistrationDomain.class);
            userRegistrationRepository.save(userRegistrationDomain);
            ConfirmationTokenDomain confirmationToken = new ConfirmationTokenDomain(userRegistrationDomain);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            log.info(confirmationToken.getConfirmationToken());
            mailMessage.setSubject(email.getSubject());
            mailMessage.setFrom("coretech2k20@gmail.com");
            mailMessage.setTo(userRegistrationDomain.getEmail());
            mailMessage.setText(email.getBody()+confirmationToken.getConfirmationToken());
            javaMailSender.send(mailMessage);
            String responseMessage = "Email has been sent to you";
            log.info(responseMessage);
            return responseMessage;
        }
    }

}
