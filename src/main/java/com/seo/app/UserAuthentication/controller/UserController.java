package com.seo.app.UserAuthentication.controller;

import com.seo.app.UserAuthentication.domain.transfer.object.AuthenticationDto;
import com.seo.app.UserAuthentication.domain.transfer.object.PasswordUpdateDto;
import com.seo.app.UserAuthentication.domain.transfer.object.SeoDto;
import com.seo.app.UserAuthentication.domain.transfer.object.UserLogInDto;
import com.seo.app.UserAuthentication.domain.transfer.object.UserRegistrationDto;
import com.seo.app.UserAuthentication.domain.transfer.object.UserUpdateDto;
import com.seo.app.UserAuthentication.services.ConfirmRegisterationService;
import com.seo.app.UserAuthentication.services.PasswordChangeService;
import com.seo.app.UserAuthentication.services.SeoService;
import com.seo.app.UserAuthentication.services.UserAuthenticationService;
import com.seo.app.UserAuthentication.services.UserLogInService;
import com.seo.app.UserAuthentication.services.UserLogoutService;
import com.seo.app.UserAuthentication.services.UserRegistrationService;
import com.seo.app.UserAuthentication.services.UserUpdateService;
import com.seo.app.UserAuthentication.services.ViewProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("seo")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserRegistrationService userRegistrationService;
    private final UserLogInService userLogInService;
    private final UserAuthenticationService userAuthenticationService;
    private final UserLogoutService userLogoutService;
    private final PasswordChangeService passwordChangeService;
    private final UserUpdateService userUpdateService;
    private final ConfirmRegisterationService confirmRegisterationService;
    private final SeoService seoService;
    private final ViewProfileService viewProfileService;

    public UserController(
            UserRegistrationService userRegistrationService,
            UserLogInService userLogInService,
            UserAuthenticationService userAuthenticationService,
            UserLogoutService userLogoutService,
            PasswordChangeService passwordChangeService,
            UserUpdateService userUpdateService,
            ConfirmRegisterationService confirmRegisterationService,
            SeoService seoService,
            ViewProfileService viewProfileService) {
        this.userRegistrationService = userRegistrationService;
        this.userLogInService = userLogInService;
        this.userAuthenticationService = userAuthenticationService;
        this.userLogoutService = userLogoutService;
        this.passwordChangeService = passwordChangeService;
        this.userUpdateService = userUpdateService;
        this.confirmRegisterationService = confirmRegisterationService;
        this.seoService = seoService;
        this.viewProfileService = viewProfileService;
    }

    @GetMapping("/profile")
    public UserRegistrationDto getProfile(@RequestParam("user_id") int id) {
        return viewProfileService.viewUser(id);
    }

    @PostMapping("/add/seo")
    public String addSeo(@RequestBody SeoDto seoDto) {
        log.info("POST /seo/add/seo body={}", seoDto);
        return seoService.addSeo(seoDto);
    }

    @PutMapping("/password/change")
    public String changePassword(@RequestBody PasswordUpdateDto passwordUpdateDto) {
        log.info("PUT /seo/password/change body={}", passwordUpdateDto);
        return passwordChangeService.changePassword(passwordUpdateDto);
    }

    @PutMapping("/user/update")
    public String updateUser(@RequestBody UserUpdateDto userUpdateDto) {
        log.info("PUT /seo/user/update body={}", userUpdateDto);
        return userUpdateService.updateUser(userUpdateDto);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        log.info("POST /seo/register body={}", userRegistrationDto);
        return userRegistrationService.registerUser(userRegistrationDto);
    }

    @PostMapping("/login/user")
    public String addLoginUser(@RequestBody UserLogInDto userLogInDto) {
        log.info("POST /seo/login/user body={}", userLogInDto);
        return userLogInService.loginUser(userLogInDto);
    }

    @PostMapping("/auth")
    public String getStatus(@RequestBody AuthenticationDto authenticationDto) {
        log.info("POST /seo/auth body={}", authenticationDto);
        return userAuthenticationService.authenticateUser(authenticationDto);
    }

    @PostMapping("/logout")
    public String logOut(@RequestParam("user_id") int userId) {
        log.info("POST /seo/logout user_id={}", userId);
        return userLogoutService.logoutUser(userId);
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(@RequestParam("token") String confirmationToken) {
        log.info("GET/POST /seo/confirm-account");
        return confirmRegisterationService.confirmRegistration(confirmationToken);
    }
}
