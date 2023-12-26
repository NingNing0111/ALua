package com.ning.aluaback.dto.register;

import lombok.Data;

/**
 * @Project: com.ning.aluaback.dto.register
 * @Author: pgthinker
 * @Date: 2023/12/26 18:21
 * @Description:
 */
@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String code;
}
