package com.school.design.automobile_sales_mall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.school.design.automobile_sales_mall.entity.User;
import com.school.design.automobile_sales_mall.entity.dto.TaskAcceptDto;
import com.school.design.automobile_sales_mall.enums.ErrorMsg;
import com.school.design.automobile_sales_mall.response.RespResult;
import com.school.design.automobile_sales_mall.service.TaskAcceptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(tags = "任务接收")
@Controller
@RequestMapping("/api")
@CrossOrigin
public class TaskAcceptController {
    @Autowired
    private TaskAcceptService taskAcceptService;

    @ApiOperation("承接任务")
    @PostMapping("/accept")
    @ResponseBody
    public RespResult taskAccept(Integer taskId, HttpServletRequest request) {
        Integer record = taskAcceptService.acceptTask(taskId, request);
        if (record < 1) {
//            model.addAttribute("msg", "任务承接失败");
            return RespResult.error("任务承接失败");
        }
//        model.addAttribute("msg", "任务承接成功");
        return RespResult.success("任务承接成功");
    }

    // 承接任务列表
    @ApiOperation("承接任务列表")
    @GetMapping("/acceptList")
    @ResponseBody
    public RespResult acceptTaskList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
//            model.addAttribute("msg", ErrorMsg.NOT_LOGIN.getMsg());
            return RespResult.error(ErrorMsg.NOT_LOGIN.getMsg());
        }
        IPage<TaskAcceptDto> iPage =  taskAcceptService.acceptTaskList(user.getId());
//        model.addAttribute("list", iPage);
        return RespResult.success(taskAcceptService.acceptTaskList(user.getId()));
    }

    @ApiOperation("取消已承接任务")
    @PostMapping("/cancel")
    @ResponseBody
    public RespResult cancelAcceptTask(Integer id) {
        Integer record = taskAcceptService.cancelAcceptTask(id);
        if (record < 1) {
            return RespResult.error("任务取消失败");
        }
        return RespResult.success("任务取消成功");
    }

    // 提交任务（完成）
    @ApiOperation("提交任务(完成)")
    @PostMapping("/submit")
    @ResponseBody
    public RespResult submitTask(Integer id) {
        Integer record = taskAcceptService.submitTask(id);
        if (record < 1) {
//            model.addAttribute("msg", "任务提交失败");
            return RespResult.error("任务提交失败");
        }
//        model.addAttribute("msg", "任务已成功提交");
        return RespResult.success("任务提交成功");
    }

}
