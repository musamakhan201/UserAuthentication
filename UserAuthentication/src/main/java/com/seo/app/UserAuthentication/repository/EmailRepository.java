package com.seo.app.UserAuthentication.repository;

import com.seo.app.UserAuthentication.domains.EmailTemplateDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailTemplateDomain, String> {
    @Query(value = "SELECT * FROM EMAIL_TEMPLATES WHERE EMAIL_TEMPLATE_ID = ?1", nativeQuery = true)
    EmailTemplateDomain findByEmailTemplateID(int id);
}
