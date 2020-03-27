package com.school.design.automobile_sales_mall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.school.design.automobile_sales_mall.entity.TaskPublish;
import com.school.design.automobile_sales_mall.entity.dto.TaskAcceptDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface TaskAcceptService {
    Integer acceptTask(Integer taskId, HttpServletRequest request);

    IPage<TaskAcceptDto> acceptTaskList(Integer userId);

    Integer cancelAcceptTask(Integer id);

    Integer submitTask(Integer id);
}
