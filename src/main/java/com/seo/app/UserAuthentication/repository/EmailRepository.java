package com.seo.app.UserAuthentication.repository;

import com.seo.app.UserAuthentication.domains.EmailTemplateDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailTemplateDomain, String> {
    @Query(value = "SELECT * FROM email_templates WHERE email_template_id = ?1", nativeQuery = true)
    EmailTemplateDomain findByEmailTemplateID(int id);
}
