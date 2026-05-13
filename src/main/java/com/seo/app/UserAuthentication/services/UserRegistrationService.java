package com.seo.app.UserAuthentication.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seo.app.UserAuthentication.domain.transfer.object.UserRegistrationDto;
import com.seo.app.UserAuthentication.domains.ConfirmationTokenDomain;
import com.seo.app.UserAuthentication.domains.UserRegistrationDomain;
import com.seo.app.UserAuthentication.repository.ConfirmationTokenRepository;
import com.seo.app.UserAuthentication.repository.UserRegistrationRepository;
import com.seo.app.UserAuthentication.security.PasswordSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    private static final Logger log = LoggerFactory.getLogger(UserRegistrationService.class);

    private final UserRegistrationRepository userRegistrationRepository;
    private final ObjectMapper objectMapper;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;
    private final PasswordSupport passwordSupport;

    public UserRegistrationService(
            UserRegistrationRepository userRegistrationRepository,
            ObjectMapper objectMapper,
            ConfirmationTokenRepository confirmationTokenRepository,
            EmailService emailService,
            PasswordSupport passwordSupport) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.objectMapper = objectMapper;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailService = emailService;
        this.passwordSupport = passwordSupport;
    }

    public String registerUser(UserRegistrationDto userRegistrationDto) {
        UserRegistrationDomain existing = userRegistrationRepository.findByEmail(userRegistrationDto.getEmail());
        if (existing != null) {
            String responseMessage = "User Already Exists With This Email";
            log.info(responseMessage);
            return responseMessage;
        }

        UserRegistrationDomain userRegistrationDomain =
                objectMapper.convertValue(userRegistrationDto, UserRegistrationDomain.class);
        userRegistrationDomain.setPassword(passwordSupport.encode(userRegistrationDto.getPassword()));
        userRegistrationRepository.save(userRegistrationDomain);

        ConfirmationTokenDomain confirmationToken = new ConfirmationTokenDomain(userRegistrationDomain);
        confirmationTokenRepository.save(confirmationToken);
        log.info("Confirmation token created for user {}", userRegistrationDomain.getEmail());

        emailService.sendRegistrationConfirmation(userRegistrationDomain, confirmationToken.getConfirmationToken());

        String responseMessage = "Email has been sent to you";
        log.info(responseMessage);
        return responseMessage;
    }
}
