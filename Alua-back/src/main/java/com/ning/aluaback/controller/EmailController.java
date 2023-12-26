package com.ning.aluaback.controller;

import com.ning.aluaback.dto.R;
import com.ning.aluaback.dto.email.EmailRequest;
import com.ning.aluaback.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project: com.ning.aluaback.controller
 * @Author: pgthinker
 * @Date: 2023/12/26 16:40
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email")
@Slf4j
public class EmailController {
    private final EmailService emailService;
    private final Integer codeLength = 6;

    @PostMapping(value = "/code")
    public ResponseEntity<R> verifyCode(@RequestBody EmailRequest emailRequest){
        log.info("{}",emailRequest);
        return ResponseEntity.ok(emailService.sendVerifyCode(emailRequest.getTargetEmail(), codeLength));
    }
}
