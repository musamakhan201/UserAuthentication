package com.seo.app.UserAuthentication.services;

import com.seo.app.UserAuthentication.domain.transfer.object.UserLogInDto;
import com.seo.app.UserAuthentication.domains.UserLogInDomain;
import com.seo.app.UserAuthentication.repository.UserLogInRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserLogInService {
    private final Logger log = LoggerFactory.getLogger(UserLogInService.class);

    @Autowired
    UserLogInRepository userLogInRepository;

    public String loginUser(UserLogInDto userLogInDto)
    {
        UserLogInDomain userLogInDomain=new UserLogInDomain();
        userLogInDomain.setUsername(userLogInDto.getUsername());
        userLogInDomain.setPassword(userLogInDto.getPassword());
        userLogInRepository.save(userLogInDomain);
        String responseMessage = "User Logged In";
        log.info(responseMessage);
        return responseMessage;
    }
}
