package com.ning.aluaback.service;

import com.ning.aluaback.dto.R;
import com.ning.aluaback.dto.chat.ChatRequest;
import com.unfbx.chatgpt.OpenAiStreamClient;
import com.unfbx.chatgpt.entity.chat.Message;

import java.util.List;

/**
 * @Project: com.ning.aluaback.service
 * @Author: pgthinker
 * @Date: 2023/12/26 23:45
 * @Description:
 */
public interface ChatService {
    /**
     * 创建chat id 用于连接websocket服务
     * @param chatRequest
     * @return chat id字符串
     */
    R create(ChatRequest chatRequest);
    boolean checkId(String chatId);
    void deleteChatId(String chatId);
    List<Message> getMessageList(String chatId);
    void addChatMessageList(String chatId,List<Message> messages);

    OpenAiStreamClient createStreamClient();
    String getUsername(String chatId);
    void charging(String chatId,List<Message> messages);
    boolean checkBalance(String chatId);
}
