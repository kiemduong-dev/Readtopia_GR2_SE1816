<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="/WEB-INF/includes/head-admin.jsp" />
<jsp:include page="/WEB-INF/includes/sidebar-admin.jsp" />
<link href="${pageContext.request.contextPath}/css/admin-style.css" rel="stylesheet" />

<div class="main-content">
    <div class="content-area">
        <h1>Add New Account</h1>

        <form method="post" action="${pageContext.request.contextPath}/admin/account/add" style="max-width: 700px; margin: auto;">
            <div class="mb-3">
                <label class="form-label">Username</label>
                <input type="text" name="username" class="form-control" required />
            </div>

            <div class="mb-3">
                <label class="form-label">Password</label>
                <input type="password" name="password" class="form-control" required />
            </div>

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label">First Name</label>
                    <input type="text" name="firstName" class="form-control" required />
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label">Last Name</label>
                    <input type="text" name="lastName" class="form-control" required />
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label">Sex</label>
                    <select name="sex" class="form-select" required>
                        <option value="1">Male</option>
                        <option value="0">Female</option>
                    </select>
                </div>

                <div class="col-md-6 mb-3">
                    <label class="form-label">Role</label>
                    <select name="role" class="form-select" required>
                        <option value="0">Admin</option>
                        <option value="1">Customer</option>
                    </select>
                </div>
            </div>

            <div class="mb-3">
                <label class="form-label">Email</label>
                <input type="email" name="email" class="form-control" required />
            </div>

            <div class="text-center mt-3">
                <button type="submit" class="btn btn-success w-100"><i class="fas fa-plus"></i> Add Account</button>
                <a href="${pageContext.request.contextPath}/admin/account/list" class="btn btn-link mt-2">← Back to Account List</a>
            </div>
        </form>
    </div>
</div>
