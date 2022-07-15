package com.cyn.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author G0dc
 * @description: 认证成功处理
 * @date 2022/6/25 22:24
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "登录成功");
        result.put("status", 200);
        result.put("authentication", authentication);
        // 设置响应格式，字符集
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        // 将result 转换成json格式
        String resultString = new ObjectMapper().writeValueAsString(result);
        // 输出
        response.getWriter().println(resultString);
    }
}
