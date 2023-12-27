package com.ning.aluaback.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
@TableName(value = "api")
public class APIKey implements Serializable {
    @TableId(value="api_id",type= IdType.AUTO)
    private Integer id;
    @TableField("api_value")
    private String value;
    @TableField("api_host")
    private String host;
}
