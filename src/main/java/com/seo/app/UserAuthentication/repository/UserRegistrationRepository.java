package com.seo.app.UserAuthentication.repository;

import com.seo.app.UserAuthentication.domains.UserRegistrationDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistrationDomain, Integer> {
    @Query(value = "SELECT * FROM users WHERE email = ?", nativeQuery = true)
    UserRegistrationDomain findByUsername(String username);
    @Query(value = "SELECT * FROM users WHERE email = ?", nativeQuery = true)
    UserRegistrationDomain findByEmail(String email);
}
