package com.seo.app.UserAuthentication.services;

import com.seo.app.UserAuthentication.domain.transfer.object.SeoDto;
import com.seo.app.UserAuthentication.domains.SeoDomain;
import com.seo.app.UserAuthentication.repository.SeoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SeoService {

    private static final Logger log = LoggerFactory.getLogger(SeoService.class);

    private final SeoRepository seoRepository;

    public SeoService(SeoRepository seoRepository) {
        this.seoRepository = seoRepository;
    }

    public String addSeo(SeoDto seoDto) {
        SeoDomain seoDomain = new SeoDomain();
        seoDomain.setSeo_type(seoDto.getSeo_type());
        seoDomain.setUrl(seoDto.getUrl());
        seoRepository.save(seoDomain);
        String responseMessage = "SEO data saved";
        log.info(responseMessage);
        return responseMessage;
    }
}
