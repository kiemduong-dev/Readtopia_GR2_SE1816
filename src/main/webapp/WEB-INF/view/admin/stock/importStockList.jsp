<%@ page import="dto.ImportStockDTO, dto.ExportStockDTO, java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/includes/head-admin.jsp" />
<jsp:include page="/WEB-INF/includes/sidebar-admin.jsp" />

<div class="main-content">
    <div class="content-area">
        <div class="page-header">
            <h2 class="page-title">Inventory Management</h2>
        </div>

        <!-- Tab Buttons -->
        <div class="tabs" style="margin-bottom: 20px;">
            <button id="importTabBtn" class="btn btn-tab active" onclick="showTab('import')">Import</button>
            <button id="exportTabBtn" class="btn btn-tab" onclick="showTab('export')">Export</button>
        </div>

        <!-- Import Stock Tab -->
        <div id="importTab" class="card">
            <div class="toolbar">
                <button class="btn btn-primary" onclick="window.location.href = '${pageContext.request.contextPath}/admin/stock/list?action=add'">
                    <i class="fas fa-plus"></i> Import Stock
                </button>
                <span style="color: #666;">Import a stock</span>
            </div>

            <div class="table-container">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Stock ID</th>
                            <th>Supplier</th>
                            <th>Staff</th>
                            <th>Import Date</th>
                            <th>Total Price</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<ImportStockDTO> list = (List<ImportStockDTO>) request.getAttribute("list");
                            if (list != null && !list.isEmpty()) {
                                for (ImportStockDTO s : list) {
                        %>
                        <tr>
                            <td><%= s.getISID()%></td>
                            <td><%= s.getSupName()%></td>
                            <td><%= s.getStaffName()%></td>
                            <td><%= s.getImportDate()%></td>
                            <td><%= String.format("%,.0f", s.getTotalPrice())%> VNĐ</td>
                            <td>
                                <a href="<%= request.getContextPath()%>/ImportStockDetailServlet?isid=<%= s.getISID()%>" 
                                   class="btn btn-icon btn-info" title="View detail of a stock">
                                    <i class="fas fa-eye"></i>
                                </a>
                            </td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr><td colspan="6">No import records found.</td></tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Export Stock Tab -->
        <div id="exportTab" class="card" style="display: none;">
            <div class="toolbar">
                <span style="color: #666;">Export order by appointed date, default is all order</span>
                <button class="btn btn-primary" onclick="exportOrderToExcel()">
                    <i class="fas fa-download"></i> Export Orders to Excel
                </button>
            </div>

            <div class="table-container">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Supplier</th>
                            <th>Staff</th>
                            <th>Order Date</th>
                            <th>Total Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<ExportStockDTO> exportList = (List<ExportStockDTO>) request.getAttribute("exportList");
                            if (exportList != null && !exportList.isEmpty()) {
                                for (ExportStockDTO s : exportList) {
                        %>
                        <tr>
                            <td><%= s.getExportID()%></td> <!-- Đúng: lấy ID của export -->
                            <td><%= s.getCustomerName()%></td> <!-- Đúng: tên khách hàng -->
                            <td><%= s.getStaffName()%></td>
                            <td><%= s.getExportDate()%></td> <!-- Đúng: ngày xuất -->
                            <td><%= String.format("%,.0f", s.getTotal())%> VND</td>
                            <td>
                                <a href="<%= request.getContextPath()%>/admin/stock/export/detail?id=<%= s.getExportID()%>" 
                                   class="btn btn-icon btn-info" title="View export details">
                                    <i class="fas fa-eye"></i>
                                </a>
                            </td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr><td colspan="5">No export records found.</td></tr>
                        <% }%>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</div>

<!-- Script to toggle tabs -->
<script>
    function showTab(tab) {
        const importTab = document.getElementById("importTab");
        const exportTab = document.getElementById("exportTab");

        const importBtn = document.getElementById("importTabBtn");
        const exportBtn = document.getElementById("exportTabBtn");

        if (tab === 'import') {
            importTab.style.display = 'block';
            exportTab.style.display = 'none';

            importBtn.classList.add('active');
            exportBtn.classList.remove('active');
        } else {
            importTab.style.display = 'none';
            exportTab.style.display = 'block';

            importBtn.classList.remove('active');
            exportBtn.classList.add('active');
        }
    }
</script>

