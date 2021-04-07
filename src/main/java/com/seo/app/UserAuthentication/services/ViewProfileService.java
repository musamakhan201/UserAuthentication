package com.seo.app.UserAuthentication.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seo.app.UserAuthentication.domain.transfer.object.UserRegistrationDto;
import com.seo.app.UserAuthentication.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewProfileService {
    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public UserRegistrationDto viewUser(int id){
        return objectMapper.convertValue(userRegistrationRepository.findById(id).get(), UserRegistrationDto.class);
    }

}
