package com.ning.aluaback.service.impl;

import com.ning.aluaback.config.EmailConfig;
import com.ning.aluaback.dto.R;
import com.ning.aluaback.dto.email.EmailResponse;
import com.ning.aluaback.entity.User;
import com.ning.aluaback.repository.UserRepository;
import com.ning.aluaback.service.CodeService;
import com.ning.aluaback.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @Project: com.ning.aluaback.service.impl
 * @Author: pgthinker
 * @Date: 2023/12/26 16:19
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final EmailConfig emailConfig;
    private final JavaMailSender javaMailSender;
    private final CodeService codeService;
    private final UserRepository userRepository;
    @Override
    public R sendVerifyCode(String targetEmail, Integer len) {
        User byEmail = userRepository.findByEmail(targetEmail);
        if(byEmail != null){
            return EmailResponse.builder().message("用户已存在").status("error").build();
        }else{
            if(codeService.hasCode(targetEmail)){
                return EmailResponse.builder().message("验证码还在有效期内").status("error").build();
            }else{
                String verifyCode = codeService.generateCode(targetEmail, len);
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(emailConfig.getFromEmail());
                message.setSubject(EmailConfig.WEBNAME + " 注册信息");
                message.setText("尊敬的用户，您的注册验证码为:" + verifyCode + ",请在5分钟以内使用，并且妥善保管，不要分享给其他人！");
                message.setTo(targetEmail);
                try{
                    javaMailSender.send(message);
                    log.info("发送了一封邮件至:{},验证码为:{}",targetEmail,verifyCode);
                    return EmailResponse.builder().message("验证码已发送").status("success").build();
                }catch (Exception e){
                    log.info("邮件发送过程中出现错误:{}",e.getMessage());
                    return EmailResponse.builder().message("验证码发送异常").status("error").build();
                }
            }
        }


    }

    @Override
    public R sendVoucherCode(String targetEmail, String voucherCode) {
        return null;
    }
}
