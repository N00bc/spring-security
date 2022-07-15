package com.cyn.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author G0dc
 * @description: 自定义认证失败信息
 * @date 2022/6/26 9:39
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "登录失败:" + exception.getMessage());
        result.put("status", "403");
        // 设置响应编码集
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        // 返回json字符串
        String failureString = new ObjectMapper().writeValueAsString(result);
        response.getWriter().write(failureString);
    }
}
