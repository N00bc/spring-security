package com.cyn.config;

import com.cyn.filter.LoginFilter;
import com.cyn.filter.MyCsrfFilter;
import com.cyn.handler.MyAuthenticationFailureHandler;
import com.cyn.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * @author G0dc
 * @description: SpringSecurity配置
 * @date 2022/7/6 13:07
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setFilterProcessesUrl("/login");
        loginFilter.setUsernameParameter("username");
        loginFilter.setPasswordParameter("password");
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setAuthenticationSuccessHandler(new MyAuthenticationSuccessHandler());
        loginFilter.setAuthenticationFailureHandler(new MyAuthenticationFailureHandler());
        return loginFilter;
    }
    @Bean
    public MyCsrfFilter csrfFilter() throws Exception {
        MyCsrfFilter csrfFilter = new MyCsrfFilter();
        csrfFilter.setFilterProcessesUrl("/login");
        csrfFilter.setUsernameParameter("username");
        csrfFilter.setCaptchaParameter("captcha");
        csrfFilter.setPasswordParameter("password");
        csrfFilter.setAuthenticationManager(authenticationManagerBean());
        csrfFilter.setAuthenticationSuccessHandler(new MyAuthenticationSuccessHandler());
        csrfFilter.setAuthenticationFailureHandler(new MyAuthenticationFailureHandler());
        return csrfFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/getVerifyCode").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .csrf()
//                .disable();
        // 将令牌保存到 cookie中， （withHttpOnlyFalse()）并且允许前端获取
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        http.addFilterAt(loginFilter(),UsernamePasswordAuthenticationFilter.class);
//        http.addFilterAt(csrfFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
