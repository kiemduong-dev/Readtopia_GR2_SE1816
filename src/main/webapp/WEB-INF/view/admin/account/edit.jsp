<%-- 
    Document   : edit
    Created on : May 27, 2025, 8:28:37 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.AccountDTO" %>
<jsp:useBean id="account" type="dto.AccountDTO" scope="request" />
<html>
    <head><title>Sửa tài khoản</title></head>
    <body>
        <h2>Chỉnh sửa tài khoản</h2>
        <form action="${pageContext.request.contextPath}/admin/account/edit" method="post">
            <input type="hidden" name="username" value="${account.username}" />
            <p>Họ tên: <input type="text" name="fullname" value="${account.fullName}" required></p>
            <p>Email: <input type="email" name="email" value="${account.email}" required></p>
            <p>Điện thoại: <input type="text" name="phone" value="${account.phone}" required></p>
            <p>Địa chỉ: <input type="text" name="address" value="${account.address}" required></p>
            <p>Vai trò: 
                <select name="role">
                    <option value="customer" ${account.role == 'customer' ? 'selected' : ''}>Customer</option>
                    <option value="staff" ${account.role == 'staff' ? 'selected' : ''}>Staff</option>
                    <option value="admin" ${account.role == 'admin' ? 'selected' : ''}>Admin</option>
                </select>
            </p>
            <p><input type="submit" value="Cập nhật"></p>
            <p style="color:red">${error}</p>
        </form>
    </body>
</html>
