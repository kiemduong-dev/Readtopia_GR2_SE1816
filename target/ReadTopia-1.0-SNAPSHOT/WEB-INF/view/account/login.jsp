<%-- 
    Document   : login
    Created on : May 27, 2025, 7:12:36 PM
    Author     : ADMIN
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../includes/header.jsp" %>


<div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
    <div class="card p-4 shadow-sm" style="width: 100%; max-width: 400px;">
        <h3 class="text-center mb-4">🔐 Đăng nhập</h3>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Tên đăng nhập</label>
                <input type="text" class="form-control" name="username" id="username" required />
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Mật khẩu</label>
                <input type="password" class="form-control" name="password" id="password" required />
            </div>
            <c:if test="${not empty error}">
                <div class="alert alert-danger py-1 small">${error}</div>
            </c:if>
            <div class="d-grid">
                <button type="submit" class="btn btn-primary">Đăng nhập</button>
            </div>
        </form>

        <div class="text-center mt-3">
            <a href="${pageContext.request.contextPath}/view/account/forgotPassword.jsp">Quên mật khẩu?</a><br>
            <a href="${pageContext.request.contextPath}/view/account/register.jsp" class="text-muted small">Chưa có tài khoản? Đăng ký</a>
        </div>
    </div>
</div>

<%@ include file="../../includes/footer.jsp" %>
