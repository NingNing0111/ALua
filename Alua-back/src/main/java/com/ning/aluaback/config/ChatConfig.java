package com.ning.aluaback.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: com.ning.aluaback.config
 * @Author: pgthinker
 * @Date: 2023/12/27 00:36
 * @Description:
 */
@Data
@Configuration
public class ChatConfig {
    private Integer chatMessageMaxLength;
    private Integer chatCost;
    public ChatConfig(){
        chatMessageMaxLength = 10;
        chatCost = 2;
    }
}
