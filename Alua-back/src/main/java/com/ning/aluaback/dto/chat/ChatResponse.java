package com.ning.aluaback.dto.chat;

import com.ning.aluaback.dto.R;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @Project: com.ning.aluaback.dto.chat
 * @Author: pgthinker
 * @Date: 2023/12/26 23:41
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class ChatResponse extends R {
    private String chatId;
}
