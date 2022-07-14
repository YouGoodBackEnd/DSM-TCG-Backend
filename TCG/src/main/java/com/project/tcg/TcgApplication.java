package com.project.tcg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class TcgApplication {

    public static void main(String[] args) {
        SpringApplication.run(TcgApplication.class, args);
    }

}