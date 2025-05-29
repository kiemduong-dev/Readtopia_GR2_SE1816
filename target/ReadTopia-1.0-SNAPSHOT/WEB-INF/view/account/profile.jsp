<%-- 
    Document   : profile
    Created on : May 27, 2025, 8:17:41 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dto.AccountDTO" %>
<%@ include file="../../includes/header.jsp" %>

<jsp:useBean id="user" type="dto.AccountDTO" scope="request" />

<div class="container mt-5 mb-5">
    <div class="card mx-auto shadow-sm" style="max-width: 600px;">
        <div class="card-body">
            <h3 class="card-title text-center mb-4">👤 Thông tin tài khoản</h3>
            <table class="table table-bordered">
                <tr><th>Username</th><td>${user.username}</td></tr>
                <tr><th>Họ tên</th><td>${user.fullName}</td></tr>
                <tr><th>Email</th><td>${user.email}</td></tr>
                <tr><th>Điện thoại</th><td>${user.phone}</td></tr>
                <tr><th>Địa chỉ</th><td>${user.address}</td></tr>
                <tr><th>Vai trò</th><td><span class="badge bg-info">${user.role}</span></td></tr>
            </table>

            <div class="text-center mt-4">
                <a href="${pageContext.request.contextPath}/edit-profile" class="btn btn-outline-primary btn-sm me-2">✏️ Chỉnh sửa hồ sơ</a>
                <a href="${pageContext.request.contextPath}/change-password" class="btn btn-outline-danger btn-sm">🔒 Đổi mật khẩu</a>
            </div>
        </div>
    </div>
</div>

<%@ include file="../../includes/footer.jsp" %>