package com.seo.app.UserAuthentication.services;

import com.seo.app.UserAuthentication.domain.transfer.object.UserLogInDto;
import com.seo.app.UserAuthentication.domains.UserLogInDomain;
import com.seo.app.UserAuthentication.repository.UserLogInRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserLogInService {

    private static final Logger log = LoggerFactory.getLogger(UserLogInService.class);

    private final UserLogInRepository userLogInRepository;

    public UserLogInService(UserLogInRepository userLogInRepository) {
        this.userLogInRepository = userLogInRepository;
    }

    /**
     * Persists a login attempt record (historical / audit table {@code login_users}).
     * Primary authentication is handled by {@link UserAuthenticationService}.
     */
    public String loginUser(UserLogInDto userLogInDto) {
        UserLogInDomain userLogInDomain = new UserLogInDomain();
        userLogInDomain.setEmail(userLogInDto.getEmail());
        userLogInDomain.setPassword(userLogInDto.getPassword());
        userLogInRepository.save(userLogInDomain);
        String responseMessage = "User Logged In";
        log.info(responseMessage);
        return responseMessage;
    }
}
