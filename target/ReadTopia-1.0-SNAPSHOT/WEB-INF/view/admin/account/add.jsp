<%-- 
    Document   : add
    Created on : May 27, 2025, 9:40:00 PM
    Author     : ADMIN
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../includes/header.jsp" %>

<div class="container mt-5 mb-5">
    <div class="card shadow-sm mx-auto" style="max-width: 600px;">
        <div class="card-body">
            <h3 class="card-title text-center mb-4">➕ Thêm tài khoản mới</h3>

            <form action="${pageContext.request.contextPath}/admin/account/add" method="post">
                <div class="mb-3">
                    <label class="form-label">Username</label>
                    <input type="text" name="username" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Mật khẩu</label>
                    <input type="password" name="password" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Họ và tên</label>
                    <input type="text" name="fullname" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" name="email" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Số điện thoại</label>
                    <input type="text" name="phone" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Địa chỉ</label>
                    <input type="text" name="address" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Vai trò</label>
                    <select name="role" class="form-select" required>
                        <option value="user">Người dùng</option>
                        <option value="staff">Nhân viên</option>
                        <option value="admin">Admin</option>
                    </select>
                </div>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger small">${error}</div>
                </c:if>

                <c:if test="${not empty success}">
                    <div class="alert alert-success small">${success}</div>
                </c:if>

                <div class="d-grid">
                    <button type="submit" class="btn btn-success">Thêm tài khoản</button>
                </div>
            </form>

            <div class="text-center mt-3">
                <a href="${pageContext.request.contextPath}/admin/account/list" class="text-muted small">← Quay lại danh sách</a>
            </div>
        </div>
    </div>
</div>

<%@ include file="../../includes/footer.jsp" %>
