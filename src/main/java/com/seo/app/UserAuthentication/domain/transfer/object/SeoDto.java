package com.seo.app.UserAuthentication.domain.transfer.object;

import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SeoDto {
    private int seo_id;
    private String seo_type;
    private String url;
}
