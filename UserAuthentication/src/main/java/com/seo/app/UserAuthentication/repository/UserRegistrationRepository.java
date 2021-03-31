package com.seo.app.UserAuthentication.repository;

import com.seo.app.UserAuthentication.domains.UserRegistrationDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistrationDomain, Integer> {
    UserRegistrationDomain findByUsername(String username);
    UserRegistrationDomain findByEmail(String email);
}
