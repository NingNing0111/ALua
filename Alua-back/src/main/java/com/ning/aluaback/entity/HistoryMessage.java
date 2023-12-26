package com.ning.aluaback.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class HistoryMessage {
    private Integer messageId;
    private Integer uerId;
    private String content;
    private Timestamp date;
}
