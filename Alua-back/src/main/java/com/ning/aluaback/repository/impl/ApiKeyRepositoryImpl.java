package com.ning.aluaback.repository.impl;

import com.ning.aluaback.entity.APIKey;
import com.ning.aluaback.mapper.ApiKeyMapper;
import com.ning.aluaback.repository.ApiKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Project: com.ning.aluaback.repository.impl
 * @Author: pgthinker
 * @Date: 2023/12/27 00:47
 * @Description:
 */
@Repository
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ApiKeyRepositoryImpl implements ApiKeyRepository {
    private final ApiKeyMapper apiKeyMapper;

    @Cacheable(value = "keys", key = "'key_all'")
    @Override
    public List<APIKey> all() {
         return apiKeyMapper.selectList(null);
    }

    @Override
    public void add(APIKey apiKey) {
        apiKeyMapper.insert(apiKey);
    }
}
