package com.demus.mapper;

import com.demus.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/5/8 20:15
 */
@Mapper
public interface UserMapper {
    SysUser selectById(Long id);
}

