package com.ning.aluaback.service;


import java.util.concurrent.TimeUnit;

/**
 * @Project: com.ning.aluaback.service
 * @Author: pgthinker
 * @Date: 2023/12/26 16:50
 * @Description:
 */
public interface RedisService {
   void set(String key, Object data);
   void setWithExpiration(String key, Object value, long timeout, TimeUnit unit);
   Object get(String key);
   void delete(String key);
   boolean hasKey(String key);
}
