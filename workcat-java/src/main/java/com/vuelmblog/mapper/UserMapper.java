package com.vuelmblog.mapper;

import com.vuelmblog.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author paulro1991
 * @since 2022-01-17
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
