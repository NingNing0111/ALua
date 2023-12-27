package com.ning.aluaback.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ning.aluaback.dto.R;
import com.ning.aluaback.dto.manage.ManageRequest;
import com.ning.aluaback.entity.User;

/**
 * @Project: com.ning.aluaback.service
 * @Author: pgthinker
 * @Date: 2023/12/27 13:49
 * @Description:
 */
public interface ManageService {
    // 封禁账号
    R banAccount(ManageRequest manageRequest);
    // 获取所有用户信息 分页
    R userInfoPage(Integer page, Integer limit);
    // 删除账号
    R deleteAccount(String username);
}
