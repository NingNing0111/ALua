package com.ning.aluaback.controller;

import com.ning.aluaback.dto.R;
import com.ning.aluaback.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: com.ning.aluaback.controller
 * @Author: pgthinker
 * @Date: 2023/12/27 10:33
 * @Description:
 */
@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    @GetMapping("/query")
    public R queryAll(@RequestParam String username,@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit){
        return messageService.query(username,page,limit);
    }
}
