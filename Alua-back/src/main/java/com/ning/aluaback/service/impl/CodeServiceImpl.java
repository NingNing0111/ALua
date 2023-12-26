package com.ning.aluaback.service.impl;

import com.ning.aluaback.service.CodeService;
import com.ning.aluaback.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

/**
 * @Project: com.ning.aluaback.service.impl
 * @Author: pgthinker
 * @Date: 2023/12/26 16:49
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {

    private final RedisService redisService;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final String prefix = "code";
    private final long time = 5L;
    @Override
    public String generateCode(String targetEmail, Integer len) {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(randomIndex));
        }
        redisService.setWithExpiration(targetEmail+":" + prefix, code.toString(), time, TimeUnit.MINUTES);
        return code.toString();
    }

    @Override
    public boolean CheckCode(String targetEmail, String code) {
        if(!redisService.hasKey(targetEmail + ":" + prefix)){
            return false;
        }else{
            String saveCode = (String) redisService.get(targetEmail + ":" + prefix);
            if(saveCode.equals(code)){
                return true;
            }else{
                return false;
            }
        }
    }

    @Override
    public void deleteCode(String targetEmail) {
        if(redisService.hasKey(targetEmail+":"+prefix)){
            redisService.delete(targetEmail+":"+prefix);
        }
    }

    @Override
    public boolean hasCode(String targetEmail) {
        return redisService.hasKey(targetEmail + ":" + prefix);
    }
}
