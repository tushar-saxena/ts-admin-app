package com.ts.tsadmin.conf;

import com.ts.tsadmin.service.repo.RepoServiceMarker;
import com.ts.tsadmin.util.Holders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
//@ComponentScan(basePackageClasses = {RepoServiceMarker.class, Holders.class})
public class Resources {

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
        messageBundle.setBasename("classpath:message");
        messageBundle.setDefaultEncoding("UTF-8");
        return messageBundle;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
