<%-- 
    Document   : edit
    Created on : May 27, 2025, 9:53:25 PM
    Author     : ADMIN
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.AccountDTO" %>
<jsp:useBean id="staff" type="dto.AccountDTO" scope="request" />
<html>
    <head>
        <title>Sửa nhân viên</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <h2>Chỉnh sửa thông tin nhân viên</h2>
        <form action="${pageContext.request.contextPath}/admin/staff/edit" method="post">
            <input type="hidden" name="username" value="${staff.username}" />
            <p>Họ tên: <input type="text" name="fullname" value="${staff.fullName}" required></p>
            <p>Email: <input type="email" name="email" value="${staff.email}" required></p>
            <p>Điện thoại: <input type="text" name="phone" value="${staff.phone}" required></p>
            <p>Địa chỉ: <input type="text" name="address" value="${staff.address}" required></p>
            <p><input type="submit" value="Cập nhật"></p>
            <p style="color:red">${error}</p>
        </fo
