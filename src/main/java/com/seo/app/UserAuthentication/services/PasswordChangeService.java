package com.seo.app.UserAuthentication.services;

import com.seo.app.UserAuthentication.domain.transfer.object.PasswordUpdateDto;
import com.seo.app.UserAuthentication.domains.UserRegistrationDomain;
import com.seo.app.UserAuthentication.repository.UserRegistrationRepository;
import com.seo.app.UserAuthentication.security.PasswordSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PasswordChangeService {

    private static final Logger log = LoggerFactory.getLogger(PasswordChangeService.class);

    private final UserRegistrationRepository userRegistrationRepository;
    private final EmailService emailService;
    private final PasswordSupport passwordSupport;

    public PasswordChangeService(
            UserRegistrationRepository userRegistrationRepository,
            EmailService emailService,
            PasswordSupport passwordSupport) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.emailService = emailService;
        this.passwordSupport = passwordSupport;
    }

    public String changePassword(PasswordUpdateDto passwordUpdateDto) {
        UserRegistrationDomain user = userRegistrationRepository
                .findById(passwordUpdateDto.getUser_id())
                .orElse(null);
        if (user == null) {
            String responseMessage = "User not found";
            log.info(responseMessage);
            return responseMessage;
        }
        if (!passwordSupport.matches(passwordUpdateDto.getOld_password(), user.getPassword())) {
            String responseMessage = "Old Password is Incorrect";
            log.info(responseMessage);
            return responseMessage;
        }
        user.setPassword(passwordSupport.encode(passwordUpdateDto.getNew_password()));
        userRegistrationRepository.save(user);
        String responseMessage = "Password Changed of ID: " + user.getUser_id();
        emailService.sendMail(2, user.getEmail());
        log.info(responseMessage);
        return responseMessage;
    }
}
