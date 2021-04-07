package com.seo.app.UserAuthentication.domain.transfer.object;

import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLogInDto {
    private int user_id;
    private String email;
    private String password;
}
