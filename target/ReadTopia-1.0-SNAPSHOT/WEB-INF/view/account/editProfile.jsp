<%-- 
    Document   : editProfile
    Created on : May 27, 2025, 8:18:18 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.AccountDTO" %>
<jsp:useBean id="user" type="dto.AccountDTO" scope="request" />
<html>
    <head><title>Chỉnh sửa hồ sơ</title></head>
    <body>
        <h2>Cập nhật thông tin cá nhân</h2>
        <form action="${pageContext.request.contextPath}/edit-profile" method="post">
            <p>Họ tên: <input type="text" name="fullname" value="${user.fullName}" required></p>
            <p>Email: <input type="email" name="email" value="${user.email}" required></p>
            <p>Số điện thoại: <input type="text" name="phone" value="${user.phone}" required></p>
            <p>Địa chỉ: <input type="text" name="address" value="${user.address}" required></p>
            <p><input type="submit" value="Cập nhật"></p>
            <p style="color:red">${error}</p>
        </form>
    </body>
</html>
