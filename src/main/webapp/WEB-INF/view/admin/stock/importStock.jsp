<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/head-admin.jsp" />
<jsp:include page="/WEB-INF/includes/sidebar-admin.jsp" />

<div class="main-content">
    <div class="content-area">
        <div class="page-header">
            <h2 class="page-title">Add New Import Stock</h2>
            <p class="page-subtitle">Fill in the form to add a new import record</p>
        </div>

        <form method="post" action="${pageContext.request.contextPath}/admin/stock/list" class="card">

            <!-- Supplier Name -->
            <div class="form-group">
                <label class="form-label" for="supplierName">Supplier Name</label>
                <input type="text" class="form-input" name="supplierName" id="supplierName" required>
            </div>

            <!-- Staff -->
            <div class="form-group">
                <label class="form-label" for="staffName">Staff</label>
                <select class="form-select" name="staffName" id="staffName" required>
                    <c:forEach var="s" items="${staffs}">
                        <option value="${s.username}">
                            ${s.firstName} ${s.lastName} (${s.username})
                        </option>
                    </c:forEach>
                </select>
            </div>

            <!-- Import Date -->
            <div class="form-group">
                <label class="form-label" for="importDate">Import Date</label>
                <input type="date" class="form-input" name="importDate" id="importDate" required>
            </div>

            <!-- Book Items -->
            <div class="form-group">
                <label class="form-label">Books</label>
                <div id="stockItems">
                    <div class="form-row book-row">
                        <div class="form-group">
                            <select class="form-select" name="bookID[]" required>
                                <c:forEach var="b" items="${books}">
                                    <option value="${b.bookID}">${b.bookTitle}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <input type="number" class="form-input" name="quantity[]" placeholder="Quantity" min="1" required>
                        </div>
                        <div class="form-group">
                            <input type="number" class="form-input" name="price[]" placeholder="Price" step="0.01" min="0" required>
                        </div>
                        <div class="form-group">
                            <button type="button" class="btn btn-icon btn-primary" onclick="addStockItem()" title="Add another book">
                                <i class="fas fa-plus"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Submit -->
            <div class="btn-group">
                <button type="submit" class="btn btn-primary">Submit</button>
                <a href="${pageContext.request.contextPath}/admin/stock/list" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
</div>

<script>
    function addStockItem() {
        const container = document.getElementById("stockItems");
        const row = container.firstElementChild.cloneNode(true);

        row.querySelectorAll("input").forEach(input => input.value = "");
        row.querySelector("select").selectedIndex = 0;

        container.appendChild(row);
    }
</script>
