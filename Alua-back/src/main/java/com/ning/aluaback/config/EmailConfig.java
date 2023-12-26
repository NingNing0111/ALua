package com.ning.aluaback.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: com.ning.aluaback.config
 * @Author: pgthinker
 * @Date: 2023/12/26 16:23
 * @Description:
 */
@Data
@Configuration
public class EmailConfig {
    public final static String WEBNAME = "Alua";
    @Value("${spring.mail.protocol}")
    private String protocol;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private Integer port;
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Value("${spring.mail.password}")
    private String fromPassword;
}
