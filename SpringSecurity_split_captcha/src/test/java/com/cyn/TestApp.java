package com.cyn;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author G0dc
 * @description: 测试
 * @date 2022/7/5 14:08
 */
@SpringBootTest
public class TestApp {
    @Test
    void testEncode() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("cyn");
        System.out.println("password = " + "{bcrypt}" + password);
    }
}
