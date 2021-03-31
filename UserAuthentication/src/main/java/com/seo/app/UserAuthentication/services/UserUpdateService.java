package com.seo.app.UserAuthentication.services;

import com.seo.app.UserAuthentication.domain.transfer.object.UserUpdateDto;
import com.seo.app.UserAuthentication.domains.UserRegistrationDomain;
import com.seo.app.UserAuthentication.repository.UserRegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateService {
    private final Logger log = LoggerFactory.getLogger(UserRegistrationService.class);

    @Autowired
    UserRegistrationRepository adminRegistrationRepository;

    @Autowired
    private EmailService emailService;

    public String updateUser(UserUpdateDto userUpdateDto)
    {
        UserRegistrationDomain user = adminRegistrationRepository.getOne(userUpdateDto.getUser_id());
        user.setFirst_name(userUpdateDto.getFirst_name());
        user.setLast_name(userUpdateDto.getLast_name());
        user.setAddress(userUpdateDto.getAddress());
        user.setEmail(userUpdateDto.getEmail());
        adminRegistrationRepository.save(user);
        emailService.sendMail(3,userUpdateDto.getEmail());
        String responseMessage = "User Updated With ID: "+user.getUser_id();
        log.info(responseMessage);
        return responseMessage;
    }
}

