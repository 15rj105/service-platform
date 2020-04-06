package com.school.design.automobile_sales_mall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.school.design.automobile_sales_mall.entity.TaskPublish;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TaskPublishService {
    boolean taskPublish(TaskPublish taskPublish, HttpServletRequest request);

    IPage<TaskPublish> taskList(Integer pageNum, Integer pageSize, String orderField, String sort, Integer catalog);
}
