package com.ning.aluaback.controller;

import com.ning.aluaback.dto.R;
import com.ning.aluaback.dto.login.LoginRequest;
import com.ning.aluaback.dto.logout.LogoutRequest;
import com.ning.aluaback.dto.register.RegisterRequest;
import com.ning.aluaback.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Project: com.ning.aluaback.controller
 * @Author: pgthinker
 * @Date: 2023/12/26 18:19
 * @Description:
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/register")
    public R register(@RequestBody RegisterRequest registerRequest){
        // 直接交给account service处理
        return accountService.register(registerRequest);
    }

    @PostMapping("/login")
    public R login(@RequestBody LoginRequest loginRequest){
        log.info("{}",loginRequest);
        return accountService.login(loginRequest);
    }

    @GetMapping("/login")
    public String login(){
        return "Hello, World!";
    }

    @PostMapping("/logout")
    public R logout(@RequestBody LogoutRequest logoutRequest){
        return accountService.logout(logoutRequest);
    }
}
