package com.foldik;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

public class Producer implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    private String name;
    private String targetUrl;
    private RestTemplate restTemplate;

    public Producer(String name, String targetUrl, RestTemplate restTemplate) {
        this.name = name;
        this.targetUrl = targetUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public void run() {
        while (true) {
            try {
                restTemplate.postForObject(targetUrl, name + UUID.randomUUID().toString(), String.class);
                Thread.sleep(2000);
            } catch (Exception t) {
                LOGGER.error(t.getMessage());
            }
        }

    }
}
