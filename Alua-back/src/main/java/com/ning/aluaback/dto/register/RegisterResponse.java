package com.ning.aluaback.dto.register;

import com.ning.aluaback.dto.R;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @Project: com.ning.aluaback.dto.register
 * @Author: pgthinker
 * @Date: 2023/12/26 18:21
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class RegisterResponse extends R {
    private String message;
}
