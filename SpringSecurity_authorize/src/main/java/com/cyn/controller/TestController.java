package com.cyn.controller;

import com.cyn.domain.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

/**
 * @author G0dc
 * @description: 接口
 * @date 2022/7/8 13:22
 */
@RestController
@RequestMapping("/hello")
public class TestController {

    @PreAuthorize("hasRole('ADMIN') and authentication.name=='root'")
    @GetMapping
    public String hello() {
        return "hello";
    }

    @PreAuthorize("authentication.name == #name")
    @GetMapping("/name")
    public String hello(String name) {
        return "hello" + name;
    }

    @PreFilter(value = "filterObject.id%2!=0", filterTarget = "users")
    @PostMapping("/users")
    public void addUsers(@RequestBody List<User> users) {
        System.out.println("users = " + users);
    }

    @PostAuthorize("returnObject == 1")
    @GetMapping("/userId")
    User getById(Integer id) {
        return new User(id, "cyn");
    }

    @PreFilter("filterObject.id%2==0")
    @GetMapping("/lists")
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) users.add(new User(i, "cyn:" + i));
        return users;
    }

    @Secured({"ROLE_USER"})
    @GetMapping("/secured")
    public User getUserByUsername() {
        return new User(99, "secured");
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/username")
    public User getUserByUsername2(String username) {
        return new User(99, username);
    }

    @DenyAll
    @GetMapping("/denyAll")
    public String denyAll() {
        return "deny All";
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/rolesAllowed")
    public String rolesAllowed() {
        return "RolesAllowed";
    }

}
