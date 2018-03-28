package com.landerer.pokemon.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.MessageFormat;

@Component
public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {

    private final static Logger log = LoggerFactory.getLogger(RestTemplateLoggingInterceptor.class);

    private static final String template = "Method: {0}, URL: {1}, Headers: {2}";

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        log.info(MessageFormat.format(template,
                httpRequest.getMethod(),
                httpRequest.getURI(),
                new String(bytes, Charset.defaultCharset())));
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
