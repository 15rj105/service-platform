package com.school.design.automobile_sales_mall.controller;

import com.school.design.automobile_sales_mall.entity.User;
import com.school.design.automobile_sales_mall.enums.ErrorMsg;
import com.school.design.automobile_sales_mall.mapper.UserMapper;
import com.school.design.automobile_sales_mall.response.RespResult;
import com.school.design.automobile_sales_mall.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(tags = "用户管理页面")
@CrossOrigin
@Controller
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @ApiOperation("登录")
    @PostMapping("/login")
    @ResponseBody
    public RespResult login(String username, String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = userService.getUserByName(username);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(3600);
                return RespResult.success(user);
            }
        }

        return RespResult.error("登陆失败");
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    @ResponseBody
    public RespResult register(@RequestBody User user) {
        if (user.getUsername() == null || user.getUsername().length() < 6 || user.getUsername().length() > 12) {
//            model.addAttribute("msg", "用户名长度在6到12之间");
            return RespResult.error("用户名长度在6到12之间");
        }
        if (user.getPassword() == null || user.getPassword().length() < 6 || user.getPassword().length() > 12) {
//            model.addAttribute("msg", "密码长度在6到12之间");
            return RespResult.error("密码长度在6到12之间");
        }
        String phoneRegex = "(13[0-9]|14[579]|15[0-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}";
        String emailRegex = "^[a-zA-Z0-9_\\.-]+@([a-zA-Z0-9-]+\\.)+[a-zA-Z0-9]{2,4}$";
        if (!user.getPhone().matches(phoneRegex)) {
//            model.addAttribute("msg", "手机号格式不正确");
            return RespResult.error("手机号格式不正确");
        }
        if (!user.getEmail().matches(emailRegex)) {
//            model.addAttribute("msg", "邮箱格式不正确");
            return RespResult.error("邮箱格式不正确");
        }
        try {
            if (userService.getUserByName(user.getUsername()) != null) {
                return RespResult.error("该用户名已被使用，请更换用户名");
            }
            int record = userService.addUser(user);
            return RespResult.success(record);
        } catch (Exception e) {
            return RespResult.error("注册失败");
        }
    }

    @ApiOperation("退出登录")
    @GetMapping("/loginout")
    @ResponseBody
    public RespResult loginOut (HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return RespResult.error(ErrorMsg.NOT_LOGIN.getMsg());
        }
        session.removeAttribute("user");

        return RespResult.success("退出登陆成功");
    }

    @ApiOperation("获取当前用户信息")
    @GetMapping("/userMessage")
    @ResponseBody
    public RespResult getUserMassage (HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if (user == null) {
//            model.addAttribute("msg", ErrorMsg.NOT_LOGIN.getMsg());
            return RespResult.error(ErrorMsg.NOT_LOGIN.getMsg());
        }
        return RespResult.success(user);
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/update")
    @ResponseBody
    public RespResult updateUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        User sessionUser = (User)session.getAttribute("user");

        if (sessionUser == null) {
//            model.addAttribute("msg", ErrorMsg.NOT_LOGIN.getMsg());
            return RespResult.error(ErrorMsg.NOT_LOGIN.getMsg());
        }
        user.setPassword(sessionUser.getPassword());
        user.setId(sessionUser.getId());
        userMapper.updateById(user);

        session.setAttribute("user", user);
        return RespResult.success(user);
    }

    @ApiOperation("修改密码")
    @PostMapping("/updatePassword")
    public RespResult updatePassword(HttpServletRequest request, String oldPwd, String newPwd) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if (user == null) {
//            model.addAttribute("msg", ErrorMsg.NOT_LOGIN.getMsg());
            return RespResult.error(ErrorMsg.NOT_LOGIN.getMsg());
        }

        if (!userService.getUserByName(user.getUsername()).getPassword().equals(oldPwd) || newPwd.length() < 6 || newPwd.length() > 12) {
            if (newPwd.length() < 6 || newPwd.length() > 12){
//                model.addAttribute("msg", "密码在6-12位");
                return RespResult.error("密码在6-12位");
            } else {
//                model.addAttribute("msg", "旧密码输入错误");
                return RespResult.error("旧密码输入错误");
            }
        }
        user.setPassword(newPwd);

        userMapper.updateById(user);

        session.setAttribute("user", user);
        return RespResult.success(user);
    }
}
