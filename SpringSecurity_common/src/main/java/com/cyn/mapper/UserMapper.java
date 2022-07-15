package com.cyn.mapper;

import com.cyn.entity.Role;
import com.cyn.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author G0dc
 * @description: 持久层
 * @date 2022/7/1 14:09
 */
@Mapper
public interface UserMapper {

    /**
     * @param username: 前端传入的用户名
     * @return com.cyn.entity.User
     * @Description: 提供用户名返回用户方法
     * @author G0dc
     * @date 2022/7/1 14:41
     */
    User getByUsername(String username);

    /**
     * @param uid: User的Id
     * @return java.util.List<com.cyn.entity.Role>
     * @Description: 根据用户的id查询所对应的权限信息
     * @author G0dc
     * @date 2022/7/1 14:42
     */
    List<Role> getRolesById(Integer uid);

    /**
     * @param username: 用户名
     * @param password: 新密码
     * @return java.lang.Integer
     * @Description: 根据用户名跟新密码
     * @author G0dc
     * @date 2022/7/6 12:55
     */
    Integer updatePassword(@Param("username") String username, @Param("password") String password);
}
