<%-- 
    Document   : login
    Created on : May 27, 2025, 7:12:36 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập</title>
</head>
<body>
    <h2>Đăng nhập hệ thống</h2>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <p>Tên đăng nhập: <input type="text" name="username" required></p>
        <p>Mật khẩu: <input type="password" name="password" required></p>
        <p><input type="submit" value="Đăng nhập"></p>
        <p style="color:red">${error}</p>
    </form>
</body>
</html>
