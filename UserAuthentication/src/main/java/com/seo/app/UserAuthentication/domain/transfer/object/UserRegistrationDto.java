package com.seo.app.UserAuthentication.domain.transfer.object;

import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class UserRegistrationDto {
    public int user_id;
    private String first_name;
    private String last_name;
    private String address;
    private String email;
    private String password;
}
