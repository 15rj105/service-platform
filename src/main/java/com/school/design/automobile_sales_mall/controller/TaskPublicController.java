package com.school.design.automobile_sales_mall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.school.design.automobile_sales_mall.entity.TaskPublish;
import com.school.design.automobile_sales_mall.entity.User;
import com.school.design.automobile_sales_mall.enums.ErrorMsg;
import com.school.design.automobile_sales_mall.service.TaskPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("api/")
public class TaskPublicController {
    @Autowired
    private TaskPublishService taskPublishService;

    @GetMapping("/publish.action")
    public String toTaskPublish(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("msg", ErrorMsg.NOT_LOGIN.getMsg());
            return "goToLogin";
        }

        return "taskPublish";
    }

    @RequestMapping("/publish.action")
    public String taskPublish(TaskPublish taskPublish, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("msg", ErrorMsg.NOT_LOGIN.getMsg());
            return "goToLogin";
        }

        if (taskPublishService.taskPublish(taskPublish, request)){
            model.addAttribute("msg", "任务发布成功！");
            return "publishSuccess";
        } else {
            model.addAttribute("msg", "任务发布失败！");
            return "publishFail";
        }
    }

    @GetMapping("/taskList.action")
    public String taskList(Integer pageNum, Integer pageSize, String orderField, String sort, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("msg", ErrorMsg.NOT_LOGIN.getMsg());
            return "goToLogin";
        }

        IPage<TaskPublish> pages = taskPublishService.taskList(pageNum, pageSize, orderField, sort, user.getId());
        model.addAttribute("taskList", pages);
        return "taskList";
    }
}
