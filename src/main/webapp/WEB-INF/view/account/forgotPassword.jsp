<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<jsp:include page="/WEB-INF/includes/head.jsp" />

<body>
    <jsp:include page="/WEB-INF/includes/header.jsp" />

    <div class="main-content">
        <div class="form-container" style="max-width: 500px; margin: 40px auto; background: #fff; border-radius: 15px; padding: 30px; box-shadow: 0 4px 15px rgba(0,0,0,0.1);">

            <!-- Logo branding -->
            <div class="logo-section">
                <div class="logo-bear"></div>
                <div class="logo-text">READTOPIA</div>
            </div>

            <h2 class="text-center" style="margin-bottom: 20px;">🔐 Forgot Password</h2>

            <!-- Thông báo lỗi hoặc thành công -->
            <c:if test="${not empty error}">
                <div class="success-message" style="background: #fdecea; color: #c62828;">
                    <i class="fas fa-times-circle"></i>
                    <span>${error}</span>
                </div>
            </c:if>
            <c:if test="${not empty success}">
                <div class="success-message">
                    <i class="fas fa-check-circle"></i>
                    <span>${success}</span>
                </div>
            </c:if>

            <!-- Form gửi yêu cầu OTP -->
            <form action="${pageContext.request.contextPath}/forgot-password" method="post">
                <div class="form-group">
                    <label class="form-label">* Username:</label>
                    <input type="text" class="form-input" name="username" required />
                </div>

                <div class="form-group">
                    <label class="form-label">* Email:</label>
                    <input type="email" class="form-input" name="email" required />
                </div>

                <div class="btn-group mt-20">
                    <button type="submit" class="btn btn-primary w-100">
                        <i class="fas fa-paper-plane"></i> Send OTP
                    </button>
                </div>

                <div class="text-center mt-20">
                    <a href="${pageContext.request.contextPath}/login" class="link">🔙 Back to Login</a>
                </div>
            </form>
        </div>
    </div>

    <jsp:include page="/WEB-INF/includes/footer.jsp" />
</body>
</html>
