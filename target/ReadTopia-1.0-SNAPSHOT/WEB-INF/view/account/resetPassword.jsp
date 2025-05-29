<%-- 
    Document   : resetPassword
    Created on : May 27, 2025, 7:23:01 PM
    Author     : ADMIN
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../includes/header.jsp" %>

<div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
    <div class="card p-4 shadow-sm" style="width: 100%; max-width: 450px;">
        <h3 class="text-center mb-3">🔒 Đặt lại mật khẩu</h3>

        <form action="${pageContext.request.contextPath}/reset-password" method="post">
            <div class="mb-3">
                <label class="form-label">Mật khẩu mới</label>
                <input type="password" name="newPassword" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Xác nhận mật khẩu mới</label>
                <input type="password" name="confirmPassword" class="form-control" required>
            </div>

            <c:if test="${not empty error}">
                <div class="alert alert-danger py-1 small">${error}</div>
            </c:if>

            <div class="d-grid">
                <button type="submit" class="btn btn-success">Cập nhật mật khẩu</button>
            </div>
        </form>

        <div class="text-center mt-3">
            <a href="${pageContext.request.contextPath}/login" class="text-muted small">← Quay lại đăng nhập</a>
        </div>
    </div>
</div>
<%@ include file="../../includes/footer.jsp" %>
