package com.ning.aluaback.dto.login;

import com.ning.aluaback.dto.R;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @Project: com.ning.aluaback.dao.login
 * @Author: pgthinker
 * @Date: 2023/12/26 15:34
 * @Description:
 */
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponse extends R {
    private String message;
    private String token;
}
