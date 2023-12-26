package com.ning.aluaback.service;

import com.ning.aluaback.dto.R;

/**
 * @Project: com.ning.aluaback.service
 * @Author: pgthinker
 * @Date: 2023/12/26 16:13
 * @Description: 邮箱发送服务
 */
public interface EmailService {

    /**
     * 发送验证码
     * @param targetEmail 目标邮箱
     * @param len  验证码长度
     * @return 是否发送成功
     */
     R sendVerifyCode(String targetEmail, Integer len);

    /**
     * 发送充值卡密
     * @param targetEmail 目标邮箱
     * @param voucherCode 卡密信息
     * @return 是否发送成功
     */
    R sendVoucherCode(String targetEmail,String voucherCode);
}
