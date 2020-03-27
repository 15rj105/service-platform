<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登陆成功跳转</title>
</head>
<body >
欢迎您，${sessionScope.user.username} ！</br>
<a href="index.action">返回首页</a></br>
<a href="userMessage.action">个人信息</a></br>
<a href="updateUser.action">用户信息修改</a></br>
<a href="updatePassword.action">密码修改</a></br>
<a href="loginOut.action">退出登录</a>
</body>
</html>
