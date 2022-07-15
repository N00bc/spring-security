package com.cyn.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author G0dc
 * @description: 测试接口
 * @date 2022/7/1 16:40
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        System.out.println("this is test page");
        return "this is test page";
    }

}
