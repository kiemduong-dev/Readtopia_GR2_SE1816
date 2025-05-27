<%-- 
    Document   : add
    Created on : May 27, 2025, 9:40:00 PM
    Author     : ADMIN
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.AccountDTO" %>
<jsp:useBean id="account" type="dto.AccountDTO" scope="request" class="dto.AccountDTO"/>
<html>
    <head><title>Thêm tài khoản</title></head>
    <body>
        <h2>Thêm tài khoản mới</h2>
        <form action="${pageContext.request.contextPath}/admin/account/add" method="post">
            <p>Tên đăng nhập: <input type="text" name="username" value="${account.username}" required></p>
            <p>Mật khẩu: <input type="password" name="password" required></p>
            <p>Họ tên: <input type="text" name="fullname" value="${account.fullName}" required></p>
            <p>Email: <input type="email" name="email" value="${account.email}" required></p>
            <p>Điện thoại: <input type="text" name="phone" value="${account.phone}" required></p>
            <p>Địa chỉ: <input type="text" name="address" value="${account.address}" required></p>
            <p>Vai trò:
                <select name="role">
                    <option value="customer">Customer</option>
                    <option value="staff">Staff</option>
                    <option value="admin">Admin</option>
                </select>
            </p>
            <p><input type="submit" value="Thêm mới"></p>
            <p style="color:red">${error}</p>
        </form>
    </body>
</html>
