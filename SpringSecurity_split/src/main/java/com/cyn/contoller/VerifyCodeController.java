package com.cyn.contoller;

import cn.hutool.captcha.LineCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author G0dc
 * @description: 生成验证码
 * @date 2022/7/3 13:30
 */
@RestController
public class VerifyCodeController {

    private LineCaptcha lineCaptcha;

    @Autowired
    public VerifyCodeController(LineCaptcha lineCaptcha) {
        this.lineCaptcha = lineCaptcha;
    }

    /**
     * @param session: 存储验证码
     * @return java.lang.String: 返回值为base64格式的字符串
     * @Description: 生成验证码并存入session
     * @author G0dc
     * @date 2022/7/3 13:34
     */

    @GetMapping("/vc.jpg")
    public String getVerifyCodeController(HttpSession session) {
        // 1.生成验证码
        lineCaptcha.createCode();
        // 2.存储验证码
        session.setAttribute("captcha", lineCaptcha.getCode());
        System.out.println(lineCaptcha.getCode());
        return lineCaptcha.getImageBase64();
    }
}
