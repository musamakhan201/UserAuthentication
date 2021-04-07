package com.seo.app.UserAuthentication.repository;

import com.seo.app.UserAuthentication.domains.SeoDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeoRepository extends JpaRepository<SeoDomain,Integer> {
}
