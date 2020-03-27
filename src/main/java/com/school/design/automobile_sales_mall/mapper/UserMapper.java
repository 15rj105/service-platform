package com.school.design.automobile_sales_mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.design.automobile_sales_mall.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User getUserByName(String userName);

    int addUser(@Param("user") User user);
}
