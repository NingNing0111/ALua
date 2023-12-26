package com.ning.aluaback.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Project: com.ning.aluaback.config
 * @Author: pgthinker
 * @Date: 2023/12/26 16:25
 * @Description:
 */
@SpringBootTest
public class EmailConfigTest {
    @Autowired
    private EmailConfig emailConfig;

    @Test
    public void emailConfigTest(){
        System.out.println(emailConfig);
    }
}
