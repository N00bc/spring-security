package com.cyn.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author G0dc
 * @description: 跨域配置
 * @date 2022/7/7 8:46
 */
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 跨域允许访问的地址
                .allowedMethods("*") // 允许跨域的方法
                .allowedOrigins("*") // 允许跨域的域
                .allowedHeaders("*") // 被允许的请求头字段
                .allowCredentials(false) // 是否需要凭证
                .exposedHeaders("") // 需要暴露的headers字段
                .maxAge(3600); // 预检请求的有效期，有效期内不必再发送预检请求
    }
}
