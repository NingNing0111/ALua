package com.ning.aluaback.service;

import com.ning.aluaback.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Project: com.ning.aluaback.service
 * @Author: pgthinker
 * @Date: 2023/12/26 16:59
 * @Description:
 */
@SpringBootTest
public class RedisServiceTest {
    @Autowired
    private RedisService redisService;
    @Test
    public void serviceTest(){
        User build = User.builder().id(0).isEnabled(true).roles(List.of("Admin")).username("admin").password("123456").build();
//        redisService.set(build.getUsername(),build);
        if(redisService.hasKey(build.getUsername())){
            User o = (User)redisService.get(build.getUsername());
            System.out.println(o);
        }
    }
    @Test
    public void deleteTest(){
        redisService.delete("zdncode@gamil.com:code");
    }
}
