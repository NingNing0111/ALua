package com.ning.aluaback.dto.manage;

import com.ning.aluaback.dto.R;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @Project: com.ning.aluaback.dto.manage
 * @Author: pgthinker
 * @Date: 2023/12/27 13:53
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class ManageResponse extends R {
    private String message;
}
