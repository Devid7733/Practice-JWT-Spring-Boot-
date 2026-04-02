package com.kshrd.demospringjwtpp;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SecurityScheme(
        name = "bearerAuth",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
@SpringBootApplication
public class DemoSpringJwtPpApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringJwtPpApplication.class, args);
    }

}
