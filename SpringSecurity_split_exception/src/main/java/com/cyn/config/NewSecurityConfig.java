//package com.cyn.config;
//
//import com.cyn.filter.LoginFilter;
//import com.cyn.handler.MyAuthenticationFailureHandler;
//import com.cyn.handler.MyAuthenticationSuccessHandler;
//import com.cyn.userDetailsService.MyUserDetailsService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author G0dc
// * @description:
// * @date 2022/7/7 17:29
// */
//@EnableWebSecurity
//public class NewSecurityConfig {
//    @Autowired
//    private MyUserDetailsService myUserDetailsService;
//
//    @Bean
//    public LoginFilter loginFilter() {
//        LoginFilter loginFilter = new LoginFilter();
//        loginFilter.setFilterProcessesUrl("/login");
//        loginFilter.setUsernameParameter("username");
//        loginFilter.setPasswordParameter("password");
//        loginFilter.setAuthenticationFailureHandler(new MyAuthenticationFailureHandler());
//        loginFilter.setAuthenticationSuccessHandler(new MyAuthenticationSuccessHandler());
//        return loginFilter;
//    }
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint((request, response, authException) -> {
//                    Map<String,Object> result = new HashMap<>();
//                    // 设置响应状态码
//                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
//                    // 设置msg
//                    result.put("msg:","登录失败");
//                    result.put("code:",HttpStatus.UNAUTHORIZED.value());
//                    result.put("exception",authException.getMessage());
//                    String resultInfo = new ObjectMapper().writeValueAsString(result);
//                    response.getWriter().write(resultInfo);
//                })
//                .accessDeniedHandler((request, response, accessDeniedException) -> {
//                    response.setStatus(HttpStatus.FORBIDDEN.value());
//                    response.getWriter().write("无权访问");
//                })
//                .and()
//                .csrf().disable();
//        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.
//    }
//}
