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
@Table(name = "seo_data")
public class SeoDomain {
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seo_id;
    private String seo_type;
    private String url;
    @Column(columnDefinition="DATETIME")
    private String seo_time;

    public SeoDomain() {
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        seo_time = utc.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
    }
}
