package com.example.payzzle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example")
public class PayzzleApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayzzleApplication.class, args);
    }

}
