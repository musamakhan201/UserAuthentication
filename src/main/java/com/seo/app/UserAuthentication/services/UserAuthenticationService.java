package com.seo.app.UserAuthentication.services;

import com.seo.app.UserAuthentication.domain.transfer.object.AuthenticationDto;
import com.seo.app.UserAuthentication.domains.UserRegistrationDomain;
import com.seo.app.UserAuthentication.repository.UserRegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {
    private final Logger log = LoggerFactory.getLogger(UserLogInService.class);

    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    public String authenticateUser(AuthenticationDto authenticationDto)
    {
        UserRegistrationDomain admin= userRegistrationRepository.findByEmail(authenticationDto.getEmail());
        if (admin!=null)
        {
            if (admin.getPassword().equals(authenticationDto.getPassword()))
            {
                if (admin.isEnabled())
                {
                    admin.setLoggedIn(true);
                    userRegistrationRepository.save(admin);
                    String responseMessage = "User Logged In With ID: "+admin.getUser_id();
                    log.info(responseMessage);
                    return responseMessage;
                }
                else{
                    String responseMessage = "Please first confirm your registration to login";
                    log.info(responseMessage);
                    return responseMessage;
                }
            }
        }
        else
        {
            String responseMessage = "Username is incorrect";
            log.info(responseMessage);
            return responseMessage;
        }
        return "Password is Incorrect";
    }
}
