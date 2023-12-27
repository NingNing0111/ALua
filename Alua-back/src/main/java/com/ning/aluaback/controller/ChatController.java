package com.ning.aluaback.controller;

import com.ning.aluaback.dto.R;
import com.ning.aluaback.dto.chat.ChatRequest;
import com.ning.aluaback.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Project: com.ning.aluaback.controller
 * @Author: pgthinker
 * @Date: 2023/12/26 23:39
 * @Description:
 */
@RestController
@RequestMapping("/api/v1/chat")
@Slf4j
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/create")
    public R createChatId(String username){
        ChatRequest chatRequest = new ChatRequest();
        chatRequest.setUsername(username);
        return chatService.create(chatRequest);
    }
}
