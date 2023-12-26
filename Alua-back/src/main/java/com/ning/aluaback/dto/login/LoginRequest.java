package com.ning.aluaback.dto.login;

import lombok.Data;

/**
 * @Project: com.ning.aluaback.dao.authentication
 * @Author: pgthinker
 * @Date: 2023/12/26 15:34
 * @Description:
 */
@Data
public class LoginRequest {
    private String email;
    private String password;
}
