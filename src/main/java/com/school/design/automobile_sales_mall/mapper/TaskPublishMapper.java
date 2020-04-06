package com.school.design.automobile_sales_mall.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.school.design.automobile_sales_mall.entity.TaskPublish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface TaskPublishMapper extends BaseMapper<TaskPublish> {
    IPage<TaskPublish> selectTaskPage(IPage<TaskPublish> page, @Param(Constants.WRAPPER) QueryWrapper<TaskPublish> queryWrapper, @Param("catalog") Integer catalog);

    int updateTask(@Param("map") Map map);
}
