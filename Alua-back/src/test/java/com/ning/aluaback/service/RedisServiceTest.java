package com.ning.aluaback.service;

import com.ning.aluaback.dto.register.RegisterRequest;
import com.ning.aluaback.entity.User;
import com.ning.aluaback.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * @Project: com.ning.aluaback.service
 * @Author: pgthinker
 * @Date: 2023/12/26 16:59
 * @Description:
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisServiceTest {
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void serviceTest(){
        User build = User.builder().id(0).isEnabled(true).roles(List.of("Admin")).username("admin").password("123456").build();

    }
    @Test
    public void deleteTest(){
        redisService.delete("zdncode@gamil.com:code");
    }
    @Test
    public void addUser(){
        userRepository.saveUser(
                User.builder()
                        .roles(List.of("USER"))
                        .isEnabled(true)
                        .username("zdncode@gmail.com")
                        .nickname("Ning")
                        .password(passwordEncoder.encode("123123"))
                        .balance(300)
                        .build()

        );
    }
}
