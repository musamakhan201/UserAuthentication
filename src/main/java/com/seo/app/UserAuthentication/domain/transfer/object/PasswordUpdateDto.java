package com.seo.app.UserAuthentication.domain.transfer.object;

import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordUpdateDto {
    private int user_id;
    private String old_password;
    private String new_password;
}
