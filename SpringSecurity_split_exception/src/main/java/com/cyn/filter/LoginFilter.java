package com.cyn.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author G0dc
 * @description: 无验证码登录页
 * @date 2022/7/6 18:26
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * @return org.springframework.security.core.Authentication
     * @Description: 自定义认证方法
     * @author G0dc
     * @date 2022/7/1 17:17
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 1.判断是否是post方式请求(UsernamePasswordAuthenticationFilter的源码)
        if (!request.getMethod().equals("POST"))
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());

        // 2.判断是否是json格式请求类型
        if (!request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE))
            return super.attemptAuthentication(request, response);

        // 3.从json数据中获取用户名密码以验证
        Map<String, String> userInfo = new HashMap<>();
        UsernamePasswordAuthenticationToken authRequest = null;
        try {
            userInfo = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            String username = userInfo.get(getUsernameParameter());
            System.out.println("username = " + username);
            String password = userInfo.get(getPasswordParameter());
            System.out.println("password = " + password);
            authRequest = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request, authRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}