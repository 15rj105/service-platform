<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登陆成功跳转</title>
</head>
<body >
<form action="updateUser.action" method="post">
    <table align="center" border="1">
        <tr>
            <td>用户名:</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>学&nbsp;&nbsp;号:</td>
            <td><input type="text" name="stuNo"></td>
        </tr>
        <tr>
            <td>手机号:</td>
            <td><input type="text" name="phone"></td>
        </tr>
        <tr>
            <td>邮&nbsp;&nbsp;箱:</td>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td><input type="submit" value="修改" name="updateUser.action"></td>
        </tr>
    </table>
</form>
</body>
</html>
