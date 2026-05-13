package com.seo.app.UserAuthentication.services;

import com.seo.app.UserAuthentication.domain.transfer.object.AuthenticationDto;
import com.seo.app.UserAuthentication.domains.UserRegistrationDomain;
import com.seo.app.UserAuthentication.repository.UserRegistrationRepository;
import com.seo.app.UserAuthentication.security.PasswordSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(UserAuthenticationService.class);

    private final UserRegistrationRepository userRegistrationRepository;
    private final PasswordSupport passwordSupport;

    public UserAuthenticationService(
            UserRegistrationRepository userRegistrationRepository, PasswordSupport passwordSupport) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.passwordSupport = passwordSupport;
    }

    public String authenticateUser(AuthenticationDto authenticationDto) {
        UserRegistrationDomain user = userRegistrationRepository.findByEmail(authenticationDto.getEmail());
        if (user == null) {
            String responseMessage = "Invalid email or password";
            log.info("Login failed: unknown email");
            return responseMessage;
        }
        if (!passwordSupport.matches(authenticationDto.getPassword(), user.getPassword())) {
            String responseMessage = "Invalid email or password";
            log.info("Login failed: bad password for {}", user.getEmail());
            return responseMessage;
        }
        if (!user.isEnabled()) {
            String responseMessage = "Please first confirm your registration to login";
            log.info(responseMessage);
            return responseMessage;
        }
        user.setLoggedIn(true);
        userRegistrationRepository.save(user);
        String responseMessage = "User Logged In With ID: " + user.getUser_id();
        log.info(responseMessage);
        return responseMessage;
    }
}
