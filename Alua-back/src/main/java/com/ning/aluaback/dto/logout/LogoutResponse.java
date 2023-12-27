package com.ning.aluaback.dto.logout;

import com.ning.aluaback.dto.R;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @Project: com.ning.aluaback.dto.logout
 * @Author: pgthinker
 * @Date: 2023/12/27 13:29
 * @Description:
 */
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
public class LogoutResponse extends R {
    private String message;
}
