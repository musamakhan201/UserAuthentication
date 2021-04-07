package com.seo.app.UserAuthentication.repository;

import com.seo.app.UserAuthentication.domains.UserLogInDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLogInRepository extends JpaRepository<UserLogInDomain,Integer> {

}
