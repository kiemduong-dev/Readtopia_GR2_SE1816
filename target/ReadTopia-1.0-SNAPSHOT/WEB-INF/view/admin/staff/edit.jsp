<%-- 
    Document   : edit
    Created on : May 27, 2025, 9:53:25 PM
    Author     : ADMIN
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.AccountDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../includes/header.jsp" %>

<jsp:useBean id="staff" type="dto.AccountDTO" scope="request" />

<div class="container mt-5 mb-5">
    <div class="card shadow-sm mx-auto" style="max-width: 600px;">
        <div class="card-body">
            <h3 class="card-title text-center mb-4">✏️ Chỉnh sửa nhân viên</h3>

            <form action="${pageContext.request.contextPath}/admin/staff/edit" method="post">
                <input type="hidden" name="username" value="${staff.username}" />

                <div class="mb-3"><label class="form-label">Họ và tên</label><input type="text" name="fullname" value="${staff.fullName}" class="form-control" required></div>
                <div class="mb-3"><label class="form-label">Email</label><input type="email" name="email" value="${staff.email}" class="form-control" required></div>
                <div class="mb-3"><label class="form-label">Số điện thoại</label><input type="text" name="phone" value="${staff.phone}" class="form-control" required></div>
                <div class="mb-3"><label class="form-label">Địa chỉ</label><input type="text" name="address" value="${staff.address}" class="form-control" required></div>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger small">${error}</div>
                </c:if>
                <c:if test="${not empty success}">
                    <div class="alert alert-success small">${success}</div>
                </c:if>

                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="../../includes/footer.jsp" %>