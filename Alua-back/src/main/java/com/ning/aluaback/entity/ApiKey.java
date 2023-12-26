package com.ning.aluaback.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project: com.ning.aluaback.entity
 * @Author: pgthinker
 * @Date: 2023/12/26 15:28
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "api_key")
public class ApiKey {
    private Integer id;
    private String key;
    private String host;
}
