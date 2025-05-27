<%-- 
    Document   : add
    Created on : May 27, 2025, 9:52:44 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.AccountDTO" %>
<jsp:useBean id="staff" type="dto.AccountDTO" scope="request" class="dto.AccountDTO"/>
<html>
    <head>
        <title>Thêm nhân viên</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <h2>Thêm nhân viên mới</h2>
        <form action="${pageContext.request.contextPath}/admin/staff/add" method="post">
            <p>Username: <input type="text" name="username" value="${staff.username}" required></p>
            <p>Password: <input type="password" name="password" required></p>
            <p>Họ tên: <input type="text" name="fullname" value="${staff.fullName}" required></p>
            <p>Email: <input type="email" name="email" value="${staff.email}" required></p>
            <p>Điện thoại: <input type="text" name="phone" value="${staff.phone}" required></p>
            <p>Địa chỉ: <input type="text" name="address" value="${staff.address}" required></p>
            <p><input type="submit" value="Thêm nhân viên"></p>
            <p style="color:red">${error}</
