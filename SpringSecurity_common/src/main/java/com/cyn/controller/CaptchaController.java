package com.cyn.controller;

import cn.hutool.captcha.LineCaptcha;
import com.cyn.util.LineCaptchaUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author G0dc
 * @description: 生成验证码接口
 * @date 2022/7/3 14:22
 */
@RestController
public class CaptchaController {

    @GetMapping("/getVerifyCode")
    public String getVerifyCode(HttpSession session) {
        // 获取验证码生成器
        LineCaptcha lineCaptcha = LineCaptchaUtils.createLineCaptcha();
        // 生成验证码
        lineCaptcha.createCode();
        String verifyCode = lineCaptcha.getCode();
        System.out.println("verifyCode = " + verifyCode);
        // 设置session
        session.setAttribute("captcha", verifyCode);
        return lineCaptcha.getImageBase64();
    }
}
