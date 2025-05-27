<%-- 
    Document   : resetPassword
    Created on : May 27, 2025, 7:23:01 PM
    Author     : ADMIN
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head><title>Đặt lại mật khẩu</title></head>
    <body>
        <h2>Đặt lại mật khẩu</h2>
        <form action="${pageContext.request.contextPath}/reset-password" method="post">
            <p>Mật khẩu mới: <input type="password" name="newPassword" required></p>
            <p>Xác nhận mật khẩu: <input type="password" name="confirmPassword" required></p>
            <p><input type="submit" value="Cập nhật"></p>
            <p style="color:red">${error}</p>
        </form>
    </body>
</html>
