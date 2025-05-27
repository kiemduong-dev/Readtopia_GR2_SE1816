<%-- 
    Document   : list
    Created on : May 27, 2025, 8:22:52 PM
    Author     : ADMIN
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dto.AccountDTO" %>

<html>
    <head>
        <title>Danh sách tài khoản</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <h2>Danh sách tài khoản</h2>

        <a href="${pageContext.request.contextPath}/admin/account/add">➕ Thêm tài khoản mới</a><br><br>

        <!-- Form tìm kiếm -->
        <form method="get" action="${pageContext.request.contextPath}/admin/account/list">
            <input type="text" name="keyword" placeholder="Tìm theo tên, email, SDT..." value="${keyword}" />
            <input type="submit" value="Tìm">
        </form>
        <br>

        <!-- Thông báo lỗi nếu có -->
        <c:if test="${not empty error}">
            <p style="color:red;">${error}</p>
        </c:if>

        <!-- Bảng danh sách tài khoản -->
        <table border="1" cellpadding="8" cellspacing="0">
            <tr style="background:#f2f2f2;">
                <th>Username</th>
                <th>Họ tên</th>
                <th>Email</th>
                <th>Điện thoại</th>
                <th>Vai trò</th>
                <th>Hành động</th>
            </tr>
            <c:forEach var="a" items="${accounts}">
                <tr>
                    <td>${a.username}</td>
                    <td>${a.fullName}</td>
                    <td>${a.email}</td>
                    <td>${a.phone}</td>
                    <td>${a.role}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/account/detail?username=${a.username}">Chi tiết</a> |
                        <a href="${pageContext.request.contextPath}/admin/account/edit?username=${a.username}">Sửa</a> |
                        <a href="${pageContext.request.contextPath}/admin/account/delete?username=${a.username}"
                           onclick="return confirm('Bạn có chắc chắn muốn xoá tài khoản này không?')">Xoá</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
