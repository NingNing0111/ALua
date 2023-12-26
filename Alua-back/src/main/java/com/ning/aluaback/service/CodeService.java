package com.ning.aluaback.service;

/**
 * @Project: com.ning.aluaback.service
 * @Author: pgthinker
 * @Date: 2023/12/26 16:47
 * @Description:
 */
public interface CodeService {
    String generateCode(String targetEmail,Integer len);
    boolean CheckCode(String targetEmail, String code);
    void deleteCode(String targetEmail);
    boolean hasCode(String targetEmail);
}
