<%-- 
    Document   : detail
    Created on : May 27, 2025, 8:25:25 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.AccountDTO" %>
<jsp:useBean id="account" type="dto.AccountDTO" scope="request" />
<html>
    <head><title>Chi tiết tài khoản</title></head>
    <body>
        <h2>Chi tiết tài khoản</h2>
        <table border="1" cellpadding="8">
            <tr><td>Tên đăng nhập:</td><td>${account.username}</td></tr>
            <tr><td>Họ tên:</td><td>${account.fullName}</td></tr>
            <tr><td>Email:</td><td>${account.email}</td></tr>
            <tr><td>Số điện thoại:</td><td>${account.phone}</td></tr>
            <tr><td>Địa chỉ:</td><td>${account.address}</td></tr>
            <tr><td>Vai trò:</td><td>${account.role}</td></tr>
        </table>
        <br>
        <a href="${pageContext.request.contextPath}/admin/account/list">Quay lại danh sách</a>
    </body>
</html>
