package com.cyn;

import com.cyn.entity.Role;
import com.cyn.entity.User;
import com.cyn.mapper.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author G0dc
 * @description: 测试
 * @date 2022/7/2 19:15
 */
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void testGetByUsername() throws JsonProcessingException {
        User user = userMapper.getByUsername("root");
        System.out.println(user);
//        String userInfo = new ObjectMapper().writeValueAsString(user);
//        System.out.println("userInfo = " + userInfo);
    }

    @Test
    void testGetById() {
        List<Role> roleList = userMapper.getRolesById(1);
        roleList.forEach(System.out::println);
    }
}
