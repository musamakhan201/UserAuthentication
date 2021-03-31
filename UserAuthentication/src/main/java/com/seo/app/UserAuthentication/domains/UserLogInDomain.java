package com.seo.app.UserAuthentication.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@ToString
@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "login_users")
public class UserLogInDomain {
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String username;
    private String password;
    @Column(columnDefinition="DATETIME")
    private String login_time;

    public UserLogInDomain() {
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        login_time = utc.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
    }
}
