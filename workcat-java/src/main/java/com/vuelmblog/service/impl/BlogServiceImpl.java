package com.vuelmblog.service.impl;

import com.vuelmblog.entity.Blog;
import com.vuelmblog.mapper.BlogMapper;
import com.vuelmblog.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
