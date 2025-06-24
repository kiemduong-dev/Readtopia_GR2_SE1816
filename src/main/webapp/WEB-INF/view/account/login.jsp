<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
    <jsp:include page="/WEB-INF/includes/head.jsp" />

    <body>
        <jsp:include page="/WEB-INF/includes/header.jsp" />

        <!-- Login Form Wrapper -->
        <div class="main-content">
            <div class="modal-content" style="margin-top: 80px;">
                <!-- Modal Header with Title -->
                <div class="modal-header">
                    <h2>🔐 Login to ReadTopia</h2>
                </div>

                <!-- Modal Body -->
                <div class="modal-body">

                    <!-- Logo branding -->
                    <div class="logo-section">
                        <div class="logo-bear"></div>
                        <div class="logo-text">READTOPIA</div>
                    </div>

                    <!-- Thông báo lỗi -->
                    <c:if test="${not empty error}">
                        <div class="success-message" style="background: #ffebee; color: #c62828;">
                            <i class="fas fa-exclamation-circle"></i>
                            ${error}
                        </div>
                    </c:if>

                    <!-- Form đăng nhập -->
                    <form action="${pageContext.request.contextPath}/login" method="post">
                        <div class="form-group">
                            <label class="form-label">* Username:</label>
                            <input type="text" class="form-input" name="username" required />
                        </div>

                        <div class="form-group">
                            <label class="form-label">* Password:</label>
                            <input type="password" class="form-input" name="password" required />
                        </div>

                        <!-- Forgot password -->
                        <div class="text-center mt-20">
                            <a href="${pageContext.request.contextPath}/forgot-password" class="link">Forgot password?</a>
                        </div>

                        <!-- Button Group -->
                        <div class="btn-group" style="margin-top: 30px;">
                            <button type="submit" class="btn btn-primary">Login</button>
                            <a href="${pageContext.request.contextPath}/register" class="btn btn-secondary">Register</a>
                        </div>

                        <!-- Back to Home -->
                        <div class="text-center mt-20">
                            <a href="${pageContext.request.contextPath}/home" class="link">⬅ Back to Home</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <jsp:include page="/WEB-INF/includes/footer.jsp" />
    </body>
</html>
