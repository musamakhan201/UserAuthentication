package com.seo.app.UserAuthentication.domain.transfer.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {
    private String email;
    private String password;

}
