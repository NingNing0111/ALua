package com.ning.aluaback.service.impl;

import com.ning.aluaback.dto.R;
import com.ning.aluaback.dto.login.LoginRequest;
import com.ning.aluaback.dto.login.LoginResponse;
import com.ning.aluaback.dto.logout.LogoutRequest;
import com.ning.aluaback.dto.logout.LogoutResponse;
import com.ning.aluaback.dto.register.RegisterRequest;
import com.ning.aluaback.dto.register.RegisterResponse;
import com.ning.aluaback.entity.User;
import com.ning.aluaback.repository.UserRepository;
import com.ning.aluaback.service.AccountService;
import com.ning.aluaback.service.CodeService;
import com.ning.aluaback.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Project: com.ning.aluaback.service.impl
 * @Author: pgthinker
 * @Date: 2023/12/26 18:34
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final CodeService codeService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public R register(RegisterRequest registerRequest) {
        String email = registerRequest.getEmail();
        String code = registerRequest.getCode();
        if(codeService.CheckCode(email,code)){
            User user = User.builder()
                    .nickname(registerRequest.getUsername())
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .username(email)
                    .build();
            userRepository.saveUser(user);
            codeService.deleteCode(email);
            return RegisterResponse.builder().message("注册成功").status("success").build();
        }else{
            return RegisterResponse.builder().message("注册异常").status("error").build();
        }
    }

    @Override
    public R login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            User user = userRepository.findByEmail(loginRequest.getEmail());
            String jwtToken = jwtService.generateToken(user);
            return LoginResponse.builder().message("登录成功").status("success").token(jwtToken).build();
        }catch (AuthenticationException e){
            return LoginResponse.builder().message("用户名或密码错误").status("error").build();
        }

    }

    @Override
    public R logout(LogoutRequest logoutRequest) {
            jwtService.deleteToken(logoutRequest.getUsername());
        return LogoutResponse.builder().message("退出成功").status("success").build();
    }
}
