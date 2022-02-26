package com.vuelmblog.controller;


import com.vuelmblog.common.lang.Result;
import com.vuelmblog.entity.User;
import com.vuelmblog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author paulro1991
 * @since 2022-01-17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequiresAuthentication //登录校验
    @GetMapping("/index")
    public Result index(){

        User user= userService.getById(1L);
        return  Result.succ(user);
    }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user){
        return  Result.succ(user);
    }

}
