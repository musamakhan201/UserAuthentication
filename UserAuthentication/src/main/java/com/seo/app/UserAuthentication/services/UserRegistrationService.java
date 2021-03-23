package com.seo.app.UserAuthentication.services;

import com.seo.app.UserAuthentication.domain.transfer.object.UserRegistrationDto;
import com.seo.app.UserAuthentication.domains.UserRegistrationDomain;
import com.seo.app.UserAuthentication.repository.UserRegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {
    private final Logger log = LoggerFactory.getLogger(UserRegistrationService.class);

    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    public String registerUser(UserRegistrationDto userRegistrationDto)
    {
        UserRegistrationDomain userRegistrationDomain=new UserRegistrationDomain();
        userRegistrationDomain.setFirst_name(userRegistrationDto.getFirst_name());
        userRegistrationDomain.setLast_name(userRegistrationDto.getLast_name());
        userRegistrationDomain.setAddress(userRegistrationDto.getAddress());
        userRegistrationDomain.setEmail(userRegistrationDto.getEmail());
        userRegistrationDomain.setUsername(userRegistrationDto.getUsername());
        userRegistrationDomain.setPassword(userRegistrationDto.getPassword());
        userRegistrationRepository.save(userRegistrationDomain);
        String responseMessage = "User has been register with ID " + userRegistrationDomain.getUser_id();
        log.info(responseMessage);
        return responseMessage;
    }

}
