<%-- 
    Document   : profile
    Created on : May 27, 2025, 8:17:41 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.AccountDTO" %>
<jsp:useBean id="user" type="dto.AccountDTO" scope="request" />
<html>
    <head>
        <title>Hồ sơ cá nhân</title>
    </head>
    <body>
        <h2>Thông tin tài khoản</h2>
        <table border="1" cellpadding="8">
            <tr><td>Tên đăng nhập:</td><td>${user.username}</td></tr>
            <tr><td>Họ tên:</td><td>${user.fullName}</td></tr>
            <tr><td>Email:</td><td>${user.email}</td></tr>
            <tr><td>Số điện thoại:</td><td>${user.phone}</td></tr>
            <tr><td>Địa chỉ:</td><td>${user.address}</td></tr>
            <tr><td>Vai trò:</td><td>${user.role}</td></tr>
        </table>
        <br>
        <a href="${pageContext.request.contextPath}/edit-profile">Chỉnh sửa hồ sơ</a> |
        <a href="${pageContext.request.contextPath}/change-password">Đổi mật khẩu</a>
    </body>
</html>
