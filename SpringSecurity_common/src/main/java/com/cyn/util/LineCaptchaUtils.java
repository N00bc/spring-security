package com.cyn.util;

import cn.hutool.captcha.LineCaptcha;

/**
 * @author G0dc
 * @description: 验证码配置类
 * @date 2022/7/3 14:23
 */
public class LineCaptchaUtils {
    private static int height = 100;
    private static int width = 200;

    public static LineCaptcha createLineCaptcha() {
        return new LineCaptcha(width, height);
    }

    public void setHeight(int height) {
        LineCaptchaUtils.height = height;
    }

    public void setWidth(int width) {
        LineCaptchaUtils.width = width;
    }
}
