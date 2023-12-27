package com.ning.aluaback.service;

import com.ning.aluaback.dto.R;
import com.ning.aluaback.dto.login.LoginRequest;
import com.ning.aluaback.dto.logout.LogoutRequest;
import com.ning.aluaback.dto.register.RegisterRequest;

/**
 * @Project: com.ning.aluaback.service
 * @Author: pgthinker
 * @Date: 2023/12/26 18:31
 * @Description:
 */
public interface AccountService {
    R register(RegisterRequest registerRequest);

    R login(LoginRequest loginRequest);

    R logout(LogoutRequest logoutRequest);
}
