package com.school.design.automobile_sales_mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.design.automobile_sales_mall.entity.TaskPublish;
import com.school.design.automobile_sales_mall.entity.User;
import com.school.design.automobile_sales_mall.mapper.TaskPublishMapper;
import com.school.design.automobile_sales_mall.service.TaskPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TaskPublishServiceImpl implements TaskPublishService {
    @Autowired
    private TaskPublishMapper taskPublishMapper;

    @Override
    @Transactional
    public boolean taskPublish(TaskPublish taskPublish, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        taskPublish.setPublishUserId(user.getId());
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        taskPublish.setPublishTime(ft.format(new Date()));
        taskPublish.setIsAccept(2);
        taskPublish.setIsComplete(2);
        taskPublish.setEnable(1);

        int insertNum = taskPublishMapper.insert(taskPublish);

        if (insertNum < 1) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public IPage<TaskPublish> taskList(Integer pageNum, Integer pageSize, String orderField, String sort, Integer userId) {
        pageNum = 1;
        pageSize = 10;
        orderField = "publish_time";
        sort = "ASC";

        IPage<TaskPublish> page = new Page<>(pageNum, pageSize);
        QueryWrapper<TaskPublish> queryWrapper = new QueryWrapper<>();
        if (sort.equals("ASC")) {
            queryWrapper.orderByAsc(orderField);
        } else {
            queryWrapper.orderByDesc(orderField);
        }

        IPage<TaskPublish> iPage = taskPublishMapper.selectTaskPage(page, queryWrapper, userId);
        return iPage;
    }
}
