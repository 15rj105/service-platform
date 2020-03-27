<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.baomidou.mybatisplus.core.metadata.IPage" %>
<%@ page import="com.school.design.automobile_sales_mall.entity.dto.TaskAcceptDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>任务列表</title>
</head>
<body >
<table align="center" border="1">
    <tr>
        <td>任务名称</td>
        <td>任务描述</td>
        <td>佣金</td>
        <td>发布人</td>
        <td>发布时间</td>
        <td>承接时间</td>
        <td>是否完成</td>
    </tr>
<%
    IPage<TaskAcceptDto> iPage = (IPage<TaskAcceptDto>) request.getAttribute("list");
    List<TaskAcceptDto> list = iPage.getRecords();
    for(int i = 0; i < list.size(); i++){
        TaskAcceptDto taskAcceptDto = (TaskAcceptDto) list.get(i);
%>
    <form action="submit.action" method="post">
        <tr>
            <td><%=taskAcceptDto.getTaskName()%></td><input type="hidden" value="<%=taskAcceptDto.getId()%>" name="id">
            <td><%=taskAcceptDto.getTaskDesc()%></td>
            <td><%=taskAcceptDto.getCharge()%></td>
            <td><%=taskAcceptDto.getPublishUser()%></td>
            <td><%=taskAcceptDto.getPublishTime()%></td>
            <td><%=taskAcceptDto.getAcceptTime()%></td>
            <%--<input type="hidden" value="<%=taskPublish.getId()%>" name="taskName">--%>
            <td><input type="submit" value="提交完成" name="submit"><td>
        </tr>
    </form>
<%
    }
%>

    </br>
    <div align="center">
        <p align="center"><a href="index.action">返回首页</a></p>
    </div>
</table>
</body>
</html>
