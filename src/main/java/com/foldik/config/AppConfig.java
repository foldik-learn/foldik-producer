package com.foldik.config;

import com.foldik.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(2);
    }

    @Bean
    public List<Producer> producers() {
        return Arrays.asList(
                new Producer(
                        "producer-1",
                        environment.getRequiredProperty("foldik.producer.target.url"),
                        restTemplate()
                ),
                new Producer(
                        "producer-2",
                        environment.getRequiredProperty("foldik.producer.target.url"),
                        restTemplate()
                )

        );
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
