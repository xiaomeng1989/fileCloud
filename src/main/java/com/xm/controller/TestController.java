package com.xm.controller;

import com.xm.entity.User;
import com.xm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 尹晓蒙
 * @date 2020-05-21 13:45
 */
@Controller
public class TestController {
    @Autowired
    private UserService userService;
    @RequestMapping("/test")
    public String test() {
        List<User> list = userService.testUser();
        for(User user : list) {
            System.out.println("姓名");
            System.out.println(user.getName());
        }
        return "hello";

    }
}
