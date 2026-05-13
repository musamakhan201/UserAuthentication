package com.seo.app.UserAuthentication.services;

import com.seo.app.UserAuthentication.domains.UserRegistrationDomain;
import com.seo.app.UserAuthentication.repository.UserRegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserLogoutService {

    private static final Logger log = LoggerFactory.getLogger(UserLogoutService.class);

    private final UserRegistrationRepository userRegistrationRepository;

    public UserLogoutService(UserRegistrationRepository userRegistrationRepository) {
        this.userRegistrationRepository = userRegistrationRepository;
    }

    public String logoutUser(int id) {
        UserRegistrationDomain user = userRegistrationRepository.findById(id).orElse(null);
        if (user == null) {
            String responseMessage = "User not found";
            log.info(responseMessage);
            return responseMessage;
        }
        user.setLoggedIn(false);
        userRegistrationRepository.save(user);
        String responseMessage = "User Logged Out With ID: " + user.getUser_id();
        log.info(responseMessage);
        return responseMessage;
    }
}
