package com.ning.aluaback.service;

import com.ning.aluaback.dto.R;
import com.ning.aluaback.entity.HistoryMessage;
import com.unfbx.chatgpt.entity.chat.Message;

import java.util.List;
/**
 * @Project: com.ning.aluaback.service
 * @Author: pgthinker
 * @Date: 2023/12/27 10:36
 * @Description:
 */
public interface MessageService {
    R query(String username, Integer page, Integer limit);
    void insert(String username, List<Message> messageList);
}
