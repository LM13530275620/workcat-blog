package com.vuelmblog.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vuelmblog.common.dto.LoginDto;
import com.vuelmblog.common.lang.Result;
import com.vuelmblog.entity.User;
import com.vuelmblog.service.UserService;
import com.vuelmblog.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){
        User user=userService.getOne(new QueryWrapper<User>().eq("username",loginDto.getUsername()));
        Assert.notNull(user,"名字不能为空或者不正确");

        if(user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){//MD5加密
            return Result.fail("密码不正确");
        }
        String jwt=jwtUtils.generateToken(user.getId());

        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");

         return Result.succ(MapUtil.builder()
                 .put("id",user.getId())
                 .put("username",user.getUsername())
                 .put("avatar",user.getAvatar())
                 .put("email",user.getEmail())
                 .map()
         );
    }

     //退出

    @RequiresAuthentication //认证之后才能退出权限
    @GetMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }
}
