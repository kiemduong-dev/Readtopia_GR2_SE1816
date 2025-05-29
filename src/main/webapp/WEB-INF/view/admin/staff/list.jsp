<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../includes/header.jsp" %>

<div class="container mt-5 mb-5">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>👥 Danh sách nhân viên</h3>
        <a href="${pageContext.request.contextPath}/admin/staff/add" class="btn btn-success btn-sm">➕ Thêm nhân viên</a>
    </div>

    <table class="table table-hover table-bordered">
        <thead class="table-light">
            <tr>
                <th>Username</th>
                <th>Họ tên</th>
                <th>Email</th>
                <th>Điện thoại</th>
                <th>Địa chỉ</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="s" items="${staffs}">
                <tr>
                    <td>${s.username}</td>
                    <td>${s.fullName}</td>
                    <td>${s.email}</td>
                    <td>${s.phone}</td>
                    <td>${s.address}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/staff/detail?username=${s.username}" class="btn btn-info btn-sm">Chi tiết</a>
                        <a href="${pageContext.request.contextPath}/admin/staff/edit?username=${s.username}" class="btn btn-warning btn-sm">Sửa</a>
                        <a href="${pageContext.request.contextPath}/admin/staff/delete?username=${s.username}" class="btn btn-danger btn-sm" onclick="return confirm('Xác nhận xoá?')">Xoá</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="../../includes/footer.jsp" %>