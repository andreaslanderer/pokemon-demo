package com.landerer.pokemon.config;

import com.landerer.pokemon.logging.RestTemplateLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class PokemonConfig {

    @Bean
    @Scope("prototype")
    public RestTemplate getRestTemplate(RestTemplateLoggingInterceptor interceptor) {
        RestTemplate template = new RestTemplate();
        template.setInterceptors(Collections.singletonList(interceptor));
        return template;
    }
}
