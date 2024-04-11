package com.hhplus.commerce.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HanghaeEcommerceApplication {

  public static void main(String[] args) {
    SpringApplication.run(HanghaeEcommerceApplication.class, args);
  }

}
