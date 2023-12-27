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
import java.sql.Timestamp;

/**
 * @Project: com.ning.aluaback.entity
 * @Author: pgthinker
 * @Date: 2023/12/26 15:25
 * @Description:
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@TableName(value = "history_message")
public class HistoryMessage implements Serializable {
    @TableId(value = "message_id", type = IdType.AUTO)
    private Integer messageId;
    @TableField(value = "u_id")
    private Integer userId;
    @TableField(value = "message_content")
    private String content;
    @TableField(value = "message_role")
    private String role;
    @TableField(value = "message_date")
    private Timestamp date;
}
