package com.ning.aluaback.repository;

import com.ning.aluaback.entity.User;

/**
 * @Project: com.ning.aluaback.repository
 * @Author: pgthinker
 * @Date: 2023/12/26 15:30
 * @Description:
 */
public interface UserRepository {
    User findByEmail(String email);
    void saveUser(User user);
}
