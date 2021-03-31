package com.seo.app.UserAuthentication.services;


import com.seo.app.UserAuthentication.domains.ConfirmationTokenDomain;
import com.seo.app.UserAuthentication.domains.UserRegistrationDomain;
import com.seo.app.UserAuthentication.repository.ConfirmationTokenRepository;
import com.seo.app.UserAuthentication.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmRegisterationService {


    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    public String confirmRegistration(String confirmationToken){
        ConfirmationTokenDomain token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        System.out.println(token);
        String response;
        if(token != null)
        {
            UserRegistrationDomain user = userRegistrationRepository.findByEmail(token.getUser().getEmail());
            user.setEnabled(true);
            userRegistrationRepository.save(user);
            response="Account Confirmation Completed!";
        }
        else
        {
            response="The link is invalid or broken!";
        }
        return response;
    }
}
