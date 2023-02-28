package com.wefox.finops.reconciliation.configuration;

import feign.auth.BasicAuthRequestInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IxopayClientConfiguration {
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(
                "one-api-live", "0294b800b81af7a7298ff49b2165cbdf3b0133df");
    }
}
