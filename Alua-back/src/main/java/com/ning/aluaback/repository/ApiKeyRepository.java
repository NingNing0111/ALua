package com.ning.aluaback.repository;

import com.ning.aluaback.entity.APIKey;

import java.util.List;

/**
 * @Project: com.ning.aluaback.repository
 * @Author: pgthinker
 * @Date: 2023/12/26 15:30
 * @Description:
 */
public interface ApiKeyRepository {
    List<APIKey> all();
    void add(APIKey apiKey);
}
