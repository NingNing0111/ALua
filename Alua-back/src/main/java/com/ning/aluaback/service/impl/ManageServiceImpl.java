package com.ning.aluaback.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ning.aluaback.dto.R;
import com.ning.aluaback.dto.manage.ManageRequest;
import com.ning.aluaback.dto.manage.ManageResponse;
import com.ning.aluaback.dto.manage.ManageResponseWithData;
import com.ning.aluaback.entity.User;
import com.ning.aluaback.repository.UserRepository;
import com.ning.aluaback.service.ManageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Project: com.ning.aluaback.service.impl
 * @Author: pgthinker
 * @Date: 2023/12/27 13:56
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ManageServiceImpl implements ManageService {
    private final UserRepository userRepository;
    @Override
    public R banAccount(ManageRequest manageRequest) {
        String username = manageRequest.getUsername();
        User user = userRepository.findByEmail(username);
        if(user.getIsEnabled()){
            user.setIsEnabled(false);
            userRepository.updateUser(user);
        }

        return ManageResponse.builder()
                .message("账号封禁成功")
                .status("success")
                .build();
    }

    @Override
    public R userInfoPage(Integer page, Integer limit) {
        IPage<User> userIPage = userRepository.userPage(page, limit);

        return ManageResponseWithData.builder()
                .message("查询成功")
                .status("success")
                .data(userIPage)
                .build();
    }

    @Override
    public R deleteAccount(String username) {
        User user = userRepository.findByEmail(username);
        userRepository.deleteUser(user);
        return ManageResponse.builder()
                .message("删除成功")
                .status("success")
                .build();
    }
}
