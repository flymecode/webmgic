package com.xupt.webmgic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebmgicApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebmgicApplication.class, args);
    }

}
