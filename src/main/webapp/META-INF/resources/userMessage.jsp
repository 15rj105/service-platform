<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户信息展示页面</title>
</head>
<body >
<h2 align="center"><font color=red>用户信息展示页面</font></h2>
<form action="updateUser.action" method="post">
    <table align="center" border="1">
        <tr>
            <td>用户名:</td>
            <td>${sessionScope.user.username}</td>
        </tr>
        <tr>
            <td>学&nbsp;&nbsp;号:</td>
            <td>${sessionScope.user.stuNo}</td>
        </tr>
        <tr>
            <td>邮&nbsp;&nbsp;箱</td>
            <td>${sessionScope.user.email}</td>
        </tr>
        <tr>
            <td>手机号:</td>
            <td>${sessionScope.user.phone}</td>
        </tr>
        <tr>
            <td>角&nbsp;&nbsp;色:</td>
            <td>${sessionScope.user.role}</td>
        </tr>
    </table>
    <p align="center"><a href="updateUser.action" color=blue>修改用户信息</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="userCenter.action">返回用户中心</a></p>
</form>

</body>
</html>
