package com.school.design.automobile_sales_mall.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.design.automobile_sales_mall.entity.TaskAccept;
import com.school.design.automobile_sales_mall.entity.User;
import com.school.design.automobile_sales_mall.entity.dto.TaskAcceptDto;
import com.school.design.automobile_sales_mall.mapper.TaskAcceptMapper;
import com.school.design.automobile_sales_mall.mapper.TaskPublishMapper;
import com.school.design.automobile_sales_mall.service.TaskAcceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskAcceptServiceImpl implements TaskAcceptService {
    @Autowired
    private TaskAcceptMapper taskAcceptMapper;

    @Autowired
    private TaskPublishMapper taskPublishMapper;

    @Override
    @Transactional
    public Integer acceptTask(Integer taskId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        TaskAccept taskAccept = new TaskAccept();

        taskAccept.setAcceptUserId(user.getId())
                .setTaskId(taskId)
                .setAcceptTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                .setIsComplete(2)
                .setEnable(2);

        Integer record = taskAcceptMapper.acceptTask(taskAccept);
        Map map = new HashMap();
        map.put("taskId", taskId);
        map.put("isAccept", 1);
        taskPublishMapper.updateTask(map);

        return record;
    }

    @Override
    public IPage<TaskAcceptDto> acceptTaskList(Integer userId) {
        Page<TaskAcceptDto> page = new Page<>(1, 20);
        IPage<TaskAcceptDto> ipage = taskAcceptMapper.acceptTaskList(page, userId);
        return ipage;
    }

    @Override
    @Transactional
    public Integer cancelAcceptTask(Integer id) {
        Map map = new HashMap();
        map.put("id", id);
        map.put("enable", 1);

        Integer taskId = taskAcceptMapper.getTaskId(id);
        Map map1 = new HashMap();
        map1.put("taskId", taskId);
        map1.put("isAccept", 2);

        Integer record = taskAcceptMapper.updateAcceptTask(map);
        taskPublishMapper.updateTask(map1);
        return record;
    }

    @Override
    @Transactional
    public Integer submitTask(Integer id) {
        Map map = new HashMap();
        map.put("id", id);
        map.put("isComplete", 1);

        Integer taskId = taskAcceptMapper.getTaskId(id);
        Map map1 = new HashMap();
        map1.put("taskId", taskId);
        map1.put("isComplete", 1);

        Integer record = taskAcceptMapper.updateAcceptTask(map);
        taskPublishMapper.updateTask(map1);

        return record;
    }
}
