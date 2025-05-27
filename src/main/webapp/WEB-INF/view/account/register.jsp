<%-- 
    Document   : register
    Created on : May 27, 2025, 7:04:36 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Đăng ký tài khoản</title>
    </head>
    <body>
        <h2>Đăng ký tài khoản</h2>
        <form action="${pageContext.request.contextPath}/register" method="post">
            <p>Tên đăng nhập: <input type="text" name="username" required></p>
            <p>Mật khẩu: <input type="password" name="password" required></p>
            <p>Họ tên: <input type="text" name="fullname" required></p>
            <p>Email: <input type="email" name="email" required></p>
            <p>Số điện thoại: <input type="text" name="phone" required></p>
            <p>Địa chỉ: <input type="text" name="address"></p>
            <p><input type="submit" value="Đăng ký"></p>
            <p style="color:red">${error}</p>
        </form>
    </body>
</html>
