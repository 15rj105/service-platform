package com.school.design.automobile_sales_mall.controller;

import com.school.design.automobile_sales_mall.entity.TaskPublish;
import com.school.design.automobile_sales_mall.entity.User;
import com.school.design.automobile_sales_mall.enums.ErrorMsg;
import com.school.design.automobile_sales_mall.response.RespResult;
import com.school.design.automobile_sales_mall.service.TaskPublishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(tags = "任务发布")
@Controller
@CrossOrigin
@RequestMapping("/api")
public class TaskPublicController {
    @Autowired
    private TaskPublishService taskPublishService;

    @ApiOperation("任务发布")
    @PostMapping("/publish")
    @ResponseBody
    public RespResult taskPublish(TaskPublish taskPublish, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
//            model.addAttribute("msg", ErrorMsg.NOT_LOGIN.getMsg());
            return RespResult.error(ErrorMsg.NOT_LOGIN.getMsg());
        }

        if (taskPublishService.taskPublish(taskPublish, request)){
//            model.addAttribute("msg", "任务发布成功！");
            return RespResult.success("任务发布成功");
        } else {
//            model.addAttribute("msg", "任务发布失败！");
            return RespResult.error("任务发布失败");
        }
    }

    @ApiOperation("任务列表")
    @GetMapping("/taskList")
    @ResponseBody
    public RespResult taskList(@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "10", required = false)Integer pageSize,
                               @RequestParam(value = "orderField", defaultValue = "publish_time", required = false) String orderField,
                               @RequestParam(value = "sort", defaultValue = "ASC", required = false) String sort,
                               @RequestParam(value = "catalog", defaultValue = "1", required = true) Integer catalog) {
        return RespResult.success(taskPublishService.taskList(pageNum, pageSize, orderField, sort, catalog));
    }
}
