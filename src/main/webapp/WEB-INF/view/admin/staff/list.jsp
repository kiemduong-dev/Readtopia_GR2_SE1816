<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Danh sách nhân viên</title>
    <meta charset="UTF-8">
</head>
<body>
    <h2>Danh sách nhân viên</h2>

    <a href="${pageContext.request.contextPath}/admin/staff/add">➕ Thêm nhân viên mới</a><br><br>

    <!-- Form tìm kiếm -->
    <form method="get" action="${pageContext.request.contextPath}/admin/staff/list">
        <input type="text" name="keyword" placeholder="Tìm kiếm theo tên, email, SDT..." value="${keyword}" />
        <input type="submit" value="Tìm">
    </form>
    <br>

    <!-- Hiển thị bảng danh sách -->
    <table border="1" cellpadding="8" cellspacing="0">
        <tr style="background:#f2f2f2;">
            <th>Username</th>
            <th>Họ tên</th>
            <th>Email</th>
            <th>Điện thoại</th>
            <th>Địa chỉ</th>
            <th>Hành động</th>
        </tr>
        <c:forEach var="s" items="${staffs}">
            <tr>
                <td>${s.username}</td>
                <td>${s.fullName}</td>
                <td>${s.email}</td>
                <td>${s.phone}</td>
                <td>${s.address}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/staff/detail?username=${s.username}">Chi tiết</a> |
                    <a href="${pageContext.request.contextPath}/admin/staff/edit?username=${s.username}">Sửa</a> |
                    <a href="${pageContext.request.contextPath}/admin/staff/delete?username=${s.username}"
                       onclick="return confirm('Bạn có chắc chắn muốn xoá nhân viên này không?')">Xoá</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
