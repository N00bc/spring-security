package com.cyn.config;

import com.cyn.securityfilter.CaptchaFilter;
import com.cyn.userDetailsService.MyUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author G0dc
 * @description: security配置类
 * @date 2022/7/3 14:50
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public SecurityConfig(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 自定义认证源
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CaptchaFilter captchaFilter() throws Exception {
        CaptchaFilter captchaFilter = new CaptchaFilter();
        // 认证参数
        captchaFilter.setUsernameParameter("username");
        captchaFilter.setCaptchaParameter("captcha");
        captchaFilter.setPasswordParameter("password");
        // 认证地址
        captchaFilter.setFilterProcessesUrl("/login");
        // 指定认证管理器
        captchaFilter.setAuthenticationManager(authenticationManagerBean());
        // 认证成功处理
        captchaFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            Map<String, Object> result = new HashMap<>();
            result.put("msg", "登录成功");
            result.put("status", HttpStatus.OK.value());
            result.put("用户信息", authentication.getPrincipal());
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            String resultInfo = new ObjectMapper().writeValueAsString(result);
            response.getWriter().write(resultInfo);
        });
        captchaFilter.setAuthenticationFailureHandler((request, response, exception) -> {
            Map<String, Object> result = new HashMap<>();
            result.put("msg", "登录失败");
            result.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.put("错误信息", exception.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            String resultInfo = new ObjectMapper().writeValueAsString(result);
            response.getWriter().write(resultInfo);
        });

        return captchaFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/getVerifyCode").permitAll()
                .mvcMatchers("/hello").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .csrf().disable();
        // 将自定义filter置于UsernamePasswordAuthenticationFilter前
        http.addFilterAt(captchaFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
