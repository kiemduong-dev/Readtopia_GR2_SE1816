<%-- 
    Document   : detail
    Created on : May 27, 2025, 9:51:35 PM
    Author     : ADMIN
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dto.AccountDTO" %>
<jsp:useBean id="staff" type="dto.AccountDTO" scope="request" />
<html>
    <head>
        <title>Chi tiết nhân viên</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <h2>Chi tiết nhân viên</h2>
        <table border="1" cellpadding="8">
            <tr><td>Tên đăng nhập:</td><td>${staff.username}</td></tr>
            <tr><td>Họ tên:</td><td>${staff.fullName}</td></tr>
            <tr><td>Email:</td><td>${staff.email}</td></tr>
            <tr><td>Điện thoại:</td><td>${staff.phone}</td></tr>
            <tr><td>Địa chỉ:</td><td>${staff.address}</td></tr>
            <tr><td>Vai trò:</td><td>${staff.role}</td></tr>
        </table>
        <br>
        <a href="${pageContext.request.contextPath}/admin/staff/list">← Quay lại danh sách</a>
    </body>
</ht
