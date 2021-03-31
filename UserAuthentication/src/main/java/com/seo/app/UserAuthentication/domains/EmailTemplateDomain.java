package com.seo.app.UserAuthentication.domains;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "email_templates")
public class EmailTemplateDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int email_template_id;
    private String subject;
    private String body;

}