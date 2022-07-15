package com.cyn.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author G0dc
 * @description: 自定义登出
 * @date 2022/6/26 10:15
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "登出成功");
        result.put("status", "200");
        response.setContentType("application/json;charset=utf-8");
        // 返回json字符串
        String failureString = new ObjectMapper().writeValueAsString(result);
        response.getWriter().write(failureString);
    }
}
