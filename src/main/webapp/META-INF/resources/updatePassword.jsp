<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登陆成功跳转</title>
</head>
<body >
<form action="updatePassword.action" method="post">
    <table align="center" border="1">
        <tr>
            <td>旧密码:</td>
            <td><input type="text" name="oldPwd"></td>
        </tr>
        <tr>
            <td>新密码:</td>
            <td><input type="text" name="newPwd"></td>
        </tr>
        <tr>
            <td><input type="submit" value="修改" name="updatePassword.action"></td>
        </tr>
    </table>
</form>
</body>
</html>
