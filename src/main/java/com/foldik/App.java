package com.foldik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.concurrent.ExecutorService;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private List<Producer> producers;

    @Autowired
    private ExecutorService executorService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        for (Producer producer : producers) {
            executorService.submit(producer);
        }
    }
}
