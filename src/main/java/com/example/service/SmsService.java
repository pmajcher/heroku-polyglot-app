package com.example.service;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static java.lang.String.format;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

@Service
public class SmsService {

    public void sendSms(String number, String text) throws IOException {
        String url = System.getenv("EASYSMS_URL") + "/messages";
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        request.addHeader("Content-Type", APPLICATION_JSON.getMimeType());
        request.setEntity(getContent(number, text));
        httpClient.execute(request);
    }

    private HttpEntity getContent(String number, String text) throws UnsupportedEncodingException {
        return new StringEntity(
                format("{ \"to\"  : \"+48%s\"," +
                        "\"body\" : \"%s\"" +
                        "}", number, text));
    }

}
