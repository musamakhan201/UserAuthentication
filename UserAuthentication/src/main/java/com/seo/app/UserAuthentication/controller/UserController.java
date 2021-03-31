package com.seo.app.UserAuthentication.controller;

import com.seo.app.UserAuthentication.domain.transfer.object.*;
import com.seo.app.UserAuthentication.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("seo")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private UserLogInService userLogInService;

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @Autowired
    private UserLogoutService userLogoutService;

    @Autowired
    private PasswordChangeService passwordChangeService;

    @Autowired
    private UserUpdateService userUpdateService;

    @Autowired
    private ConfirmRegisterationService confirmRegisterationService;


    @RequestMapping(value = "/password/change", method = RequestMethod.PUT)
    public String changePassword(@RequestBody PasswordUpdateDto passwordUpdateDto){
        log.info("POST Call received at User/change Password with DTO" + passwordUpdateDto);
        return passwordChangeService.changePassword(passwordUpdateDto);
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.PUT)
    public String updateUser(@RequestBody UserUpdateDto userUpdateDto) {
        log.info("POST Call received at User/update with DTO" + userUpdateDto);
        return userUpdateService.updateUser(userUpdateDto);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        log.info("POST Call received at user/register with DTO" + userRegistrationDto);
        return userRegistrationService.registerUser(userRegistrationDto);
    }

    @RequestMapping(value = "/login/user", method = RequestMethod.POST)
    public String addLoginUser(@RequestBody UserLogInDto userLogInDto) {
        log.info("POST Call received at user/add with DTO" + userLogInDto);
        return userLogInService.loginUser(userLogInDto);
    }

    @PostMapping(path = "/auth")
    public String getStatus(@RequestBody final AuthenticationDto authenticationDto) {
        log.info("POST Call received at user/login with DTO" + authenticationDto);
        return userAuthenticationService.authenticateUser(authenticationDto);
    }

    @PostMapping(path = "/logout")
    public String logOut(int user_id) {
        log.info("POST Call received at user/logout with ID" + user_id);
        return userLogoutService.logoutUser(user_id);
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(@RequestParam("token")String confirmationToken)
    {
        log.info("POST Call received for confirm registration");
        return confirmRegisterationService.confirmRegistration(confirmationToken);
    }
}
