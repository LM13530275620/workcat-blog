package com.vuelmblog.service.impl;

import com.vuelmblog.entity.User;
import com.vuelmblog.mapper.UserMapper;
import com.vuelmblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author paulro1991
 * @since 2022-01-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
