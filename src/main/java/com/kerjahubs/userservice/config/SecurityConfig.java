package com.kerjahubs.userservice.config;

import com.kerjahubs.common.config.BaseSecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@ComponentScan(basePackages = {"com.kerjahubs.common"})
public class SecurityConfig extends BaseSecurityConfig {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.headers().contentSecurityPolicy("script-src 'self'");
        http.antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/**").permitAll();
        super.configure(http);
    }
}
