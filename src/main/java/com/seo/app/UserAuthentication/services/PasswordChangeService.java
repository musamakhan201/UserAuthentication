package com.seo.app.UserAuthentication.services;

import com.seo.app.UserAuthentication.domain.transfer.object.PasswordUpdateDto;
import com.seo.app.UserAuthentication.domains.UserRegistrationDomain;
import com.seo.app.UserAuthentication.repository.UserRegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordChangeService {
    private final Logger log = LoggerFactory.getLogger(UserRegistrationService.class);

    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    @Autowired
    private EmailService emailService;

    public String changePassword(PasswordUpdateDto passwordUpdateDto)
    {
        UserRegistrationDomain user = userRegistrationRepository.getOne(passwordUpdateDto.getUser_id());
        if (user.getPassword().equals(passwordUpdateDto.getOld_password()))
        {
            user.setPassword(passwordUpdateDto.getNew_password());
            userRegistrationRepository.save(user);
            String responseMessage = "Password Changed of ID: "+user.getUser_id();
            emailService.sendMail(2,user.getEmail());
            log.info(responseMessage);
            return responseMessage;
        }
        String responseMessage = "Old Password is Incorrect";
        log.info(responseMessage);
        return responseMessage;
    }
}
