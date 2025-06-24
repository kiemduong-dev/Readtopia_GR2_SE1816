<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <jsp:include page="/WEB-INF/includes/head.jsp" />

    <body>
        <jsp:include page="/WEB-INF/includes/header.jsp" />

        <div class="main-content">
            <div class="form-container" style="max-width: 500px; margin: 50px auto;">
                <!-- Logo thương hiệu -->
                <div class="logo-section text-center">
                    <div class="logo-bear"></div>
                    <div class="logo-text">READTOPIA</div>
                </div>

                <!-- Tiêu đề -->
                <h2 class="text-center mb-3">📨 Verify OTP</h2>

                <!-- Mô tả -->
                <p class="text-center mb-4">
                    An OTP code has been sent to your email:
                    <strong>${sessionScope.resetEmail}</strong><br />
                    Please enter the 6-digit code below to continue.
                </p>

                <!-- Thông báo lỗi -->
                <c:if test="${not empty error}">
                    <div class="success-message" style="background: #ffebee; color: #c62828;">
                        <i class="fas fa-exclamation-circle"></i> ${error}
                    </div>
                </c:if>

                <!-- Form nhập OTP -->
                <form action="${pageContext.request.contextPath}/verify-otp-reset" method="post">
                    <div class="form-group">
                        <label class="form-label">* OTP Code:</label>
                        <input type="text"
                               class="form-input"
                               name="otp"
                               required
                               maxlength="6"
                               pattern="[0-9]{6}"
                               placeholder="Enter 6-digit OTP" />
                    </div>

                    <div class="btn-group">
                        <button type="submit" class="btn btn-primary w-100">✅ Verify</button>
                    </div>
                </form>

                <!-- Gửi lại OTP -->
                <div class="text-center mt-20">
                    <a href="${pageContext.request.contextPath}/forgot-password" class="link">
                        🔁 Resend OTP
                    </a>
                </div>
            </div>
        </div>

        <jsp:include page="/WEB-INF/includes/footer.jsp" />
    </body>
</html>
