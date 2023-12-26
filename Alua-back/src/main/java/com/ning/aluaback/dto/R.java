package com.ning.aluaback.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @Project: com.ning.aluaback.dto
 * @Author: pgthinker
 * @Date: 2023/12/26 17:20
 * @Description:
 */
@SuperBuilder
@Data
public abstract class R {
    private String status;
}
