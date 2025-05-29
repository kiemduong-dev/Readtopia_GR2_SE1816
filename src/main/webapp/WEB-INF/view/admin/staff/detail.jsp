<%-- 
    Document   : detail
    Created on : May 27, 2025, 9:51:35 PM
    Author     : ADMIN
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dto.AccountDTO" %>
<%@ include file="../../includes/header.jsp" %>

<jsp:useBean id="staff" type="dto.AccountDTO" scope="request" />

<div class="container mt-5 mb-5">
    <div class="card shadow-sm mx-auto" style="max-width: 600px;">
        <div class="card-body">
            <h3 class="card-title text-center mb-4">👤 Chi tiết nhân viên</h3>
            <table class="table table-bordered">
                <tr><th>Username</th><td>${staff.username}</td></tr>
                <tr><th>Họ và tên</th><td>${staff.fullName}</td></tr>
                <tr><th>Email</th><td>${staff.email}</td></tr>
                <tr><th>Điện thoại</th><td>${staff.phone}</td></tr>
                <tr><th>Địa chỉ</th><td>${staff.address}</td></tr>
                <tr><th>Vai trò</th><td><span class="badge bg-primary">${staff.role}</span></td></tr>
            </table>
            <div class="text-center mt-3">
                <a href="${pageContext.request.contextPath}/admin/staff/edit?username=${staff.username}" class="btn btn-outline-warning btn-sm">✏️ Chỉnh sửa</a>
                <a href="${pageContext.request.contextPath}/admin/staff/list" class="btn btn-outline-secondary btn-sm">← Quay lại</a>
            </div>
        </div>
    </div>
</div>

<%@ include file="../../includes/footer.jsp" %>