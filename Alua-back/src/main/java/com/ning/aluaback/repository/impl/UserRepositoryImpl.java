package com.ning.aluaback.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.aluaback.entity.User;
import com.ning.aluaback.mapper.UserMapper;
import com.ning.aluaback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Project: com.ning.aluaback.repository.impl
 * @Author: pgthinker
 * @Date: 2023/12/26 18:28
 * @Description:
 */

@Repository
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor = Exception.class)
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

    @Override
    public void updateUser(User user) {
        userMapper.update(user,null);
    }

    @Override
    public void deleteUser(User user) {
        userMapper.deleteById(user);
    }

    @Override
    public IPage<User> userPage(Integer page, Integer limit) {
        Page<User> objectPage = new Page<>(page, limit);
        return userMapper.selectPage(objectPage,null);
    }
}
