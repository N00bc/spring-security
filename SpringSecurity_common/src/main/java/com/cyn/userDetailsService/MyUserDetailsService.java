package com.cyn.userDetailsService;

import com.cyn.entity.User;
import com.cyn.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @author G0dc
 * @description: 数据库认证
 * @date 2022/7/4 10:05
 */
@Component
public class MyUserDetailsService implements UserDetailsService, UserDetailsPasswordService {


    private final UserMapper userMapper;

    @Autowired
    public MyUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getByUsername(username);
        if (ObjectUtils.isEmpty(user)) throw new UsernameNotFoundException("用户名不存在");
        // 存在需要设置user的权限信息
        user.setRoles(userMapper.getRolesById(user.getId()));
        return user;
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        Integer i = userMapper.updatePassword(user.getUsername(), newPassword);
        if (i != null) {
            return user;
        }
        throw new RuntimeException("更新异常");
    }
}
