<%-- 
    Document   : changePassword
    Created on : May 27, 2025, 8:20:47 PM
    Author     : ADMIN
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../includes/header.jsp" %>

<div class="container mt-5 mb-5">
    <div class="card mx-auto shadow-sm" style="max-width: 500px;">
        <div class="card-body">
            <h3 class="text-center mb-4">🔒 Đổi mật khẩu</h3>

            <form action="${pageContext.request.contextPath}/change-password" method="post">
                <div class="mb-3">
                    <label class="form-label">Mật khẩu hiện tại</label>
                    <input type="password" name="currentPassword" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Mật khẩu mới</label>
                    <input type="password" name="newPassword" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Xác nhận mật khẩu mới</label>
                    <input type="password" name="confirmPassword" class="form-control" required>
                </div>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger small">${error}</div>
                </c:if>

                <c:if test="${not empty success}">
                    <div class="alert alert-success small">${success}</div>
                </c:if>

                <div class="d-grid">
                    <button type="submit" class="btn btn-danger">Cập nhật mật khẩu</button>
                </div>
            </form>

            <div class="text-center mt-3">
                <a href="${pageContext.request.contextPath}/profile" class="text-muted small">← Quay lại hồ sơ</a>
            </div>
        </div>
    </div>
</div>

<%@ include file="../../includes/footer.jsp" %>
