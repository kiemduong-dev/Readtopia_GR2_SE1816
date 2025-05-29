<%-- 
    Document   : edit
    Created on : May 27, 2025, 8:28:37 PM
    Author     : ADMIN
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dto.AccountDTO" %>
<%@ include file="../../includes/header.jsp" %>

<jsp:useBean id="account" type="dto.AccountDTO" scope="request" />

<div class="container mt-5 mb-5">
    <div class="card shadow-sm mx-auto" style="max-width: 600px;">
        <div class="card-body">
            <h3 class="card-title text-center mb-4">✏️ Chỉnh sửa tài khoản</h3>

            <form action="${pageContext.request.contextPath}/admin/account/edit" method="post">
                <input type="hidden" name="username" value="${account.username}"/>

                <div class="mb-3">
                    <label class="form-label">Họ và tên</label>
                    <input type="text" name="fullname" value="${account.fullName}" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" name="email" value="${account.email}" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Số điện thoại</label>
                    <input type="text" name="phone" value="${account.phone}" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Địa chỉ</label>
                    <input type="text" name="address" value="${account.address}" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Vai trò</label>
                    <select name="role" class="form-select" required>
                        <option value="admin" ${account.role == 'admin' ? 'selected' : ''}>Admin</option>
                        <option value="staff" ${account.role == 'staff' ? 'selected' : ''}>Nhân viên</option>
                        <option value="user" ${account.role == 'user' ? 'selected' : ''}>Người dùng</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label">Trạng thái</label>
                    <select name="accStatus" class="form-select" required>
                        <option value="1" ${account.accStatus == 1 ? 'selected' : ''}>Hoạt động</option>
                        <option value="0" ${account.accStatus == 0 ? 'selected' : ''}>Khoá</option>
                    </select>
                </div>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger small">${error}</div>
                </c:if>

                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                </div>
            </form>

            <div class="text-center mt-3">
                <a href="${pageContext.request.contextPath}/admin/account/list" class="text-muted small">← Quay lại danh sách</a>
            </div>
        </div>
    </div>
</div>

<%@ include file="../../includes/footer.jsp" %>