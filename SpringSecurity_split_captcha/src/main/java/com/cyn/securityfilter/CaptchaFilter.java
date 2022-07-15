package com.cyn.securityfilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author G0dc
 * @description: 自定义过滤器
 * @date 2022/7/3 14:56
 */
public class CaptchaFilter extends UsernamePasswordAuthenticationFilter {

    public static final String FORM_CAPTCHA_KEY = "captcha";
    private String captchaParameter = FORM_CAPTCHA_KEY;

    public String getCaptchaParameter() {
        return captchaParameter;
    }

    public void setCaptchaParameter(String captchaParameter) {
        this.captchaParameter = captchaParameter;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 不是Post方法直接抛出异常
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        // 从request中获取参数
        UsernamePasswordAuthenticationToken authToken = null;
        try {
            Map<String, String> userInfo = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            // 用户输入的验证码
            String captcha = userInfo.get(this.getCaptchaParameter());
            // 获取session中的验证码
            String sessionCaptcha = (String) request.getSession().getAttribute("captcha");
            if (!ObjectUtils.isEmpty(captcha) && !ObjectUtils.isEmpty(sessionCaptcha) && sessionCaptcha.equalsIgnoreCase(captcha)) {
                String username = userInfo.get(this.getUsernameParameter());
                String password = userInfo.get(this.getPasswordParameter());
                authToken = new UsernamePasswordAuthenticationToken(username, password);
                setDetails(request, authToken);
                return this.getAuthenticationManager().authenticate(authToken);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new AuthenticationServiceException("验证码异常");
    }
}
