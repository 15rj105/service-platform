package com.school.design.automobile_sales_mall.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.design.automobile_sales_mall.entity.TaskAccept;
import com.school.design.automobile_sales_mall.entity.TaskPublish;
import com.school.design.automobile_sales_mall.entity.dto.TaskAcceptDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TaskAcceptMapper extends BaseMapper<TaskAccept> {
    Integer acceptTask(@Param("taskAccept") TaskAccept taskAccept);

    Integer updateAcceptTask(@Param("map") Map map);

    IPage<TaskAcceptDto> acceptTaskList(Page<TaskAcceptDto> page, @Param("userId") Integer userId);

    Integer getTaskId(@Param("id") Integer id);
}
