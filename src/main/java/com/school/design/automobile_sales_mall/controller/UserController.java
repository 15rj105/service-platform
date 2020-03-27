package com.school.design.automobile_sales_mall.controller;

import com.school.design.automobile_sales_mall.entity.User;
import com.school.design.automobile_sales_mall.enums.ErrorMsg;
import com.school.design.automobile_sales_mall.mapper.UserMapper;
import com.school.design.automobile_sales_mall.response.RespResult;
import com.school.design.automobile_sales_mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin
@Controller
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/login.action")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/login.action")
    public String login(String username, String password, HttpSession session, Model model) {
        User user = userService.getUserByName(username);
        ModelAndView mv = new ModelAndView("index");
        if (user != null) {
            if (user.getPassword().equals(password)) {
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(3600);
//                mv.addObject("user", user);
                model.addAttribute("user", user);
                return "index";
            }
        }
        model.addAttribute("msg", "账号或密码不正确");
//        mv.setViewName("login");
        return "loginFail";
    }

    @GetMapping("/register.action")
    public String toRegister() {
        return "register";
    }

    @PostMapping("/register")
    public RespResult toRegister(User user) {
//        if (username == null || username.length() < 6 || username.length() > 12) {
//            model.addAttribute("msg", "用户名长度在6到12之间");
//            return "fail";
//        }
//        if (password == null || password.length() < 6 || password.length() > 12) {
//            model.addAttribute("msg", "密码长度在6到12之间");
//            return "fail";
//        }
//        String phoneRegex = "(13[0-9]|14[579]|15[0-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}";
//        String emailRegex = "^[a-zA-Z0-9_\\.-]+@([a-zA-Z0-9-]+\\.)+[a-zA-Z0-9]{2,4}$";
//        if (!phone.matches(phoneRegex)) {
//            model.addAttribute("msg", "手机号格式不正确");
//            return "fail";
//        }
//        if (!email.matches(emailRegex)) {
//            model.addAttribute("msg", "邮箱格式不正确");
//            return "fail";
//        }
        try {
            int record = userService.addUser(user);
            return RespResult.success(record);
        } catch (Exception e) {
            return RespResult.error("注册失败");
        }
    }

    @GetMapping("/index.action")
    public String index() {
        return "index";
    }

    @GetMapping("/loginOut.action")
    public String loginOut (HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("msg", ErrorMsg.NOT_LOGIN.getMsg());
            return "goToLogin";
        }
        session.removeAttribute("user");

        return "login";
    }

    @GetMapping("/userMessage.action")
    public String getUserMassage (HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if (user == null) {
            model.addAttribute("msg", ErrorMsg.NOT_LOGIN.getMsg());
            return "goToLogin";
        }
        return "userMessage";
    }

    @GetMapping("/updateUser.action")
    public String toUpdateUser(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if (user == null) {
            model.addAttribute("msg", ErrorMsg.NOT_LOGIN.getMsg());
            return "goToLogin";
        }
        return "updateUser";
    }


    @PostMapping("/updateUser.action")
    public String updateUser(HttpServletRequest request, User user, Model model) {
        HttpSession session = request.getSession();
        User sessionUser = (User)session.getAttribute("user");

        if (sessionUser == null) {
            model.addAttribute("msg", ErrorMsg.NOT_LOGIN.getMsg());
            return "goToLogin";
        }
        user.setPassword(sessionUser.getPassword());
        user.setId(sessionUser.getId());
        userMapper.updateById(user);

        session.setAttribute("user", user);
        return "updateSuccess";
    }

    @PostMapping("/updatePassword.action")
    public String updatePassword(HttpServletRequest request, String oldPwd, String newPwd, Model model) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if (user == null) {
            model.addAttribute("msg", ErrorMsg.NOT_LOGIN.getMsg());
            return "goToLogin";
        }

        if (!userService.getUserByName(user.getUsername()).getPassword().equals(oldPwd) || newPwd.length() < 6 || newPwd.length() > 12) {
            if (newPwd.length() < 6 || newPwd.length() > 12){
                model.addAttribute("msg", "密码在6-12位");
            } else {
                model.addAttribute("msg", "旧密码输入错误");
            }
            return "updatePasswordFail";
        }
        user.setPassword(newPwd);

        userMapper.updateById(user);

        session.setAttribute("user", user);
        return "updatePasswordSuccess";
    }

    @GetMapping("/userCenter.action")
    public String toUserCenter(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if (user == null) {
            model.addAttribute("msg", ErrorMsg.NOT_LOGIN.getMsg());
            return "goToLogin";
        }
        return "userCenter";
    }

    @GetMapping("/updatePassword.action")
    public String toUpdatePassword(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if (user == null) {
            model.addAttribute("msg", ErrorMsg.NOT_LOGIN.getMsg());
            return "goToLogin";
        }
        return "updatePassword";
    }
}
