<%-- 
    Document   : list
    Created on : May 27, 2025, 8:22:52 PM
    Author     : ADMIN
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../includes/header.jsp" %>

<div class="container mt-5 mb-5">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>📋 Danh sách tài khoản</h3>
        <a href="${pageContext.request.contextPath}/admin/account/add" class="btn btn-success btn-sm">➕ Thêm tài khoản mới</a>
    </div>

    <form method="get" action="${pageContext.request.contextPath}/admin/account/list" class="row g-2 mb-3">
        <div class="col-md-4">
            <input type="text" name="keyword" value="${keyword}" class="form-control" placeholder="Tìm kiếm theo username, tên, email...">
        </div>
        <div class="col-md-2">
            <button type="submit" class="btn btn-outline-primary w-100">🔍 Tìm kiếm</button>
        </div>
    </form>

    <c:if test="${not empty error}">
        <div class="alert alert-danger small">${error}</div>
    </c:if>

    <table class="table table-hover table-bordered">
        <thead class="table-light">
            <tr>
                <th>Username</th>
                <th>Họ tên</th>
                <th>Email</th>
                <th>Điện thoại</th>
                <th>Vai trò</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="a" items="${accounts}">
                <tr>
                    <td>${a.username}</td>
                    <td>${a.fullName}</td>
                    <td>${a.email}</td>
                    <td>${a.phone}</td>
                    <td><span class="badge bg-info text-dark">${a.role}</span></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/account/detail?username=${a.username}" class="btn btn-sm btn-info">Chi tiết</a>
                        <a href="${pageContext.request.contextPath}/admin/account/edit?username=${a.username}" class="btn btn-sm btn-warning">Sửa</a>
                        <a href="${pageContext.request.contextPath}/admin/account/delete?username=${a.username}" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xoá?')">Xoá</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="../../includes/footer.jsp" %>