package com.wefox.finops.reconciliation.configuration;

import feign.auth.BasicAuthRequestInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IxopayClientConfiguration {
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(@Value("${ixopay.user}") final String user,
        @Value("${ixopay.password}") final String password) {
        return new BasicAuthRequestInterceptor(user, password);
    }
}
