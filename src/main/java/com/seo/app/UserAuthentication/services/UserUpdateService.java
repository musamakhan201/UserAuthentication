package com.seo.app.UserAuthentication.services;

import com.seo.app.UserAuthentication.domain.transfer.object.UserUpdateDto;
import com.seo.app.UserAuthentication.domains.UserRegistrationDomain;
import com.seo.app.UserAuthentication.repository.UserRegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateService {

    private static final Logger log = LoggerFactory.getLogger(UserUpdateService.class);

    private final UserRegistrationRepository userRegistrationRepository;
    private final EmailService emailService;

    public UserUpdateService(UserRegistrationRepository userRegistrationRepository, EmailService emailService) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.emailService = emailService;
    }

    public String updateUser(UserUpdateDto userUpdateDto) {
        UserRegistrationDomain user = userRegistrationRepository
                .findById(userUpdateDto.getUser_id())
                .orElse(null);
        if (user == null) {
            String responseMessage = "User not found";
            log.info(responseMessage);
            return responseMessage;
        }
        user.setFirst_name(userUpdateDto.getFirst_name());
        user.setLast_name(userUpdateDto.getLast_name());
        user.setAddress(userUpdateDto.getAddress());
        user.setEmail(userUpdateDto.getEmail());
        userRegistrationRepository.save(user);
        emailService.sendMail(3, userUpdateDto.getEmail());
        String responseMessage = "User Updated With ID: " + user.getUser_id();
        log.info(responseMessage);
        return responseMessage;
    }
}
