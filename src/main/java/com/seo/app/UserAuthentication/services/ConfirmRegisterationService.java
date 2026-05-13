package com.seo.app.UserAuthentication.services;

import com.seo.app.UserAuthentication.domains.ConfirmationTokenDomain;
import com.seo.app.UserAuthentication.domains.UserRegistrationDomain;
import com.seo.app.UserAuthentication.repository.ConfirmationTokenRepository;
import com.seo.app.UserAuthentication.repository.UserRegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ConfirmRegisterationService {

    private static final Logger log = LoggerFactory.getLogger(ConfirmRegisterationService.class);

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRegistrationRepository userRegistrationRepository;

    public ConfirmRegisterationService(
            ConfirmationTokenRepository confirmationTokenRepository,
            UserRegistrationRepository userRegistrationRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.userRegistrationRepository = userRegistrationRepository;
    }

    public String confirmRegistration(String confirmationToken) {
        ConfirmationTokenDomain token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token == null) {
            log.info("Invalid confirmation token");
            return "The link is invalid or broken!";
        }
        UserRegistrationDomain user = userRegistrationRepository.findByEmail(token.getUser().getEmail());
        if (user == null) {
            log.warn("Token valid but user missing for email {}", token.getUser().getEmail());
            return "The link is invalid or broken!";
        }
        user.setEnabled(true);
        userRegistrationRepository.save(user);
        log.info("Account confirmed for {}", user.getEmail());
        return "Account Confirmation Completed!";
    }
}
