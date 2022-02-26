package com.vuelmblog.shiro;


import com.vuelmblog.entity.User;
import com.vuelmblog.service.UserService;
import com.vuelmblog.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;


    public boolean supports(AuthenticationToken token){
        return token instanceof JwtToken;

    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken=(JwtToken) token;
        String userId=jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();

        User user=userService.getById(Long.valueOf(userId));

        if(user==null){
            throw new UnknownAccountException("账号不存在");
        }
        if(user.getStatus()==-1){
            throw  new LockedAccountException("账号已被锁定");
        }

        // 将查询的数据copy到AccountProfile类
        AccountProfile profile=new AccountProfile();
        BeanUtils.copyProperties(user,profile);



        System.out.println("------------");

       return new  SimpleAuthenticationInfo(profile,jwtToken.getCredentials(),getName());
    }
}