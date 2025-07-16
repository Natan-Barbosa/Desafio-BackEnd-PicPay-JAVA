package com.barbosa.desafiobackendpicpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DesafiobackendpicpayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafiobackendpicpayApplication.class, args);
    }

}
