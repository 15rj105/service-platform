package com.school.design.automobile_sales_mall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.school.design.automobile_sales_mall.entity.User;
import com.school.design.automobile_sales_mall.entity.dto.TaskAcceptDto;
import com.school.design.automobile_sales_mall.enums.ErrorMsg;
import com.school.design.automobile_sales_mall.service.TaskAcceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("api/")
public class TaskAcceptController {
    @Autowired
    private TaskAcceptService taskAcceptService;

    @PostMapping("/accept.action")
    public String taskAccept(Integer taskId, HttpServletRequest request, Model model) {
        Integer record = taskAcceptService.acceptTask(taskId, request);
        if (record < 1) {
            model.addAttribute("msg", "任务承接失败");
            return "acceptFail";
        }
        model.addAttribute("msg", "任务承接成功");
        return "acceptSuccess";
    }

    // 承接任务列表
    @GetMapping("/acceptList.action")
    public String acceptTaskList(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("msg", ErrorMsg.NOT_LOGIN.getMsg());
            return "goToLogin";
        }
        IPage<TaskAcceptDto> iPage =  taskAcceptService.acceptTaskList(user.getId());
        model.addAttribute("list", iPage);
        return "acceptList";
    }

    // 取消已承接任务
    public String cancelAcceptTask(Integer id) {
        Integer record = taskAcceptService.cancelAcceptTask(id);
        if (record < 1) {
            return "cancelFail";
        }
        return "cancelSucess";
    }

    // 提交任务（完成）
    @PostMapping("/submit.action")
    public String submitTask(Integer id, Model model) {
        Integer record = taskAcceptService.submitTask(id);
        if (record < 1) {
            model.addAttribute("msg", "任务提交失败");
            return "submitFail";
        }
        model.addAttribute("msg", "任务已成功提交");
        return "submitSuccess";
    }

}
