package com.ning.aluaback.repository;

import com.ning.aluaback.entity.APIKey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Project: com.ning.aluaback.repository
 * @Author: pgthinker
 * @Date: 2023/12/27 01:24
 * @Description:
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiKeyRepositoryTest {
    @Autowired
    private ApiKeyRepository apiKeyRepository;
    @Test
    public void addTest(){
        apiKeyRepository.add(APIKey.builder().value("sk-cz2Q9hPT2E6gDYJH1cC90098599a4f1191F6E00069D2C38d").host("https://api.mnzdna.xyz/").build());
    }
}
