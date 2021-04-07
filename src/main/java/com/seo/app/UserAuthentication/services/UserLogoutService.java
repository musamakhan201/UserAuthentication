package com.seo.app.UserAuthentication.services;

import com.seo.app.UserAuthentication.domains.UserRegistrationDomain;
import com.seo.app.UserAuthentication.repository.UserRegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLogoutService {
    private final Logger log = LoggerFactory.getLogger(UserLogInService.class);

    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    public String logoutUser(int id)
    {
        UserRegistrationDomain user = userRegistrationRepository.getOne(id);
        user.setLoggedIn(false);
        userRegistrationRepository.save(user);
        String responseMessage = "User Logged Out With ID: "+user.getUser_id();
        log.info(responseMessage);
        return responseMessage;
    }
}
