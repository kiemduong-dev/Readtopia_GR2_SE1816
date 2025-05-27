<%-- 
    Document   : forgotPassword
    Created on : May 27, 2025, 7:22:55 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head><title>Quên mật khẩu</title></head>
    <body>
        <h2>Quên mật khẩu</h2>
        <form action="${pageContext.request.contextPath}/forgot-password" method="post">
            <p>Email: <input type="email" name="email" required></p>
            <p><input type="submit" value="Gửi yêu cầu"></p>
            <p style="color:red">${error}</p>
        </form>
    </body>
</html>
