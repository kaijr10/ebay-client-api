package com.ebay.ebayclientapi.config;

import java.util.Collections;

import javax.print.attribute.standard.Media;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class HttpRequestUtility {

    private HttpHeaders buildHttpHeaders(MediaType mediaType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return httpHeaders;
    }

    public HttpHeaders buildHttpHeaders(MediaType mediaType, String accessToken) {
        HttpHeaders httpHeaders = buildHttpHeaders(mediaType);
        httpHeaders.set("Authorization", "Bearer " + accessToken);
        return httpHeaders;
    }
}
