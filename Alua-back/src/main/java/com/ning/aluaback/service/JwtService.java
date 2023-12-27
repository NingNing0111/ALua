package com.ning.aluaback.service;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

/**
 * @Project: com.ning.aluaback.service
 * @Author: pgthinker
 * @Date: 2023/12/26 15:31
 * @Description:
 */
public interface JwtService {
    /**
     * 生成Token
     * @param userDetails
     * @return token值
     */
    String generateToken(UserDetails userDetails);

    /**
     * 从token中提取Email
     * @param token
     * @return email
     */
    String extractEmail(String token);

    /**
     * 从token中提取所有数据
     * @param token
     * @param claimsTFunction
     * @return
     * @param <T>
     */
    <T> T extractClaim(String token, Function<Claims, T> claimsTFunction);

    /**
     * 校验token是否有效
     * @param token
     * @param userDetails
     * @return
     */
    boolean isTokenValid(String token, UserDetails userDetails);

    void deleteToken(String username);


}
