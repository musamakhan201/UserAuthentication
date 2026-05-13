package com.seo.app.UserAuthentication.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seo.app.UserAuthentication.domain.transfer.object.UserRegistrationDto;
import com.seo.app.UserAuthentication.repository.UserRegistrationRepository;
import org.springframework.stereotype.Service;

@Service
public class ViewProfileService {

    private final UserRegistrationRepository userRegistrationRepository;
    private final ObjectMapper objectMapper;

    public ViewProfileService(UserRegistrationRepository userRegistrationRepository, ObjectMapper objectMapper) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.objectMapper = objectMapper;
    }

    public UserRegistrationDto viewUser(int id) {
        return userRegistrationRepository
                .findById(id)
                .map(user -> objectMapper.convertValue(user, UserRegistrationDto.class))
                .orElse(null);
    }
}
