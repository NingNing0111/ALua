package com.ning.aluaback.dto.email;

import com.ning.aluaback.dto.R;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @Project: com.ning.aluaback.dto.email
 * @Author: pgthinker
 * @Date: 2023/12/26 16:44
 * @Description:
 */

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class EmailResponse extends R {
    private String message;
}
