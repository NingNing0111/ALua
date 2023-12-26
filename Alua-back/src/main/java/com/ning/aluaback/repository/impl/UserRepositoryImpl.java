package com.ning.aluaback.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ning.aluaback.entity.User;
import com.ning.aluaback.mapper.UserMapper;
import com.ning.aluaback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @Project: com.ning.aluaback.repository.impl
 * @Author: pgthinker
 * @Date: 2023/12/26 18:28
 * @Description:
 */

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository {
    private final UserMapper userMapper;

    @Override
    public User findByEmail(String username) {
        QueryWrapper<User> wp = new QueryWrapper<>();
        wp.eq("u_email",username);
        User user = userMapper.selectOne(wp);
        return user;
    }


    @Override
    public void saveUser(User user) {
        userMapper.insert(user);
    }
}
