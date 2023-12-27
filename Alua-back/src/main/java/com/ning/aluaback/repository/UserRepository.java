package com.ning.aluaback.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ning.aluaback.entity.User;
import org.springframework.data.relational.core.sql.In;

/**
 * @Project: com.ning.aluaback.repository
 * @Author: pgthinker
 * @Date: 2023/12/26 15:30
 * @Description:
 */
public interface UserRepository {
    User findByEmail(String email);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    IPage<User> userPage(Integer page, Integer limit);
}
