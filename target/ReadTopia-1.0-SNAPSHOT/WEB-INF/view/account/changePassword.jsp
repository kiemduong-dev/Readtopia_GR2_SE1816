<%-- 
    Document   : changePassword
    Created on : May 27, 2025, 8:20:47 PM
    Author     : ADMIN
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head><title>Đổi mật khẩu</title></head>
    <body>
        <h2>Đổi mật khẩu</h2>
        <form action="${pageContext.request.contextPath}/change-password" method="post">
            <p>Mật khẩu cũ: <input type="password" name="oldPassword" required></p>
            <p>Mật khẩu mới: <input type="password" name="newPassword" required></p>
            <p>Xác nhận mật khẩu: <input type="password" name="confirmPassword" required></p>
            <p><input type="submit" value="Cập nhật"></p>
            <p style="color:red">${error}</p>
        </form>
    </body>
</html>
