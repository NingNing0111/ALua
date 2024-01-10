package com.ning.aluaback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AluaBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(AluaBackApplication.class, args);
    }

}
