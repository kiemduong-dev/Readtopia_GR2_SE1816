// Page navigation
function showPage(pageId) {
    // Hide all pages
    const pages = document.querySelectorAll('.page');
    pages.forEach(page => page.classList.remove('active'));

    // Show selected page
    document.getElementById(pageId).classList.add('active');

    // Update active menu item
    const menuItems = document.querySelectorAll('.menu-item');
    menuItems.forEach(item => item.classList.remove('active'));
    event.target.classList.add('active');
}

// Initialize revenue chart
function initRevenueChart() {
    const ctx = document.getElementById('revenueChart').getContext('2d');
    new Chart(ctx, {
        type: 'line',
        data: {
            labels: [], // dữ liệu thời gian sẽ được load từ server
            datasets: [
                // dữ liệu import, export, revenue sẽ được fetch và đưa vào ở backend hoặc API
            ]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    position: 'top',
                }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        callback: function (value) {
                            return value.toLocaleString() + ' VNĐ';
                        }
                    }
                }
            }
        }
    });
}

// Modal functions - chỉ mở modal, không gán dữ liệu mẫu
function showAddAccountModal() {
    document.getElementById('addAccountModal').style.display = 'block';
}

function viewAccount(username) {
    // TODO: load dữ liệu account từ backend và gán vào modal trước khi show
    document.getElementById('viewAccountModal').style.display = 'block';
}

function editAccount(username) {
    // TODO: load dữ liệu account từ backend và gán vào modal trước khi show
    document.getElementById('editAccountModal').style.display = 'block';
}

let deleteAccountUsername = '';
function deleteAccount(username) {
    deleteAccountUsername = username;
    document.getElementById('deleteMessage').textContent = `Do you want to delete ${username} account?`;
    document.getElementById('deleteConfirmModal').style.display = 'block';
}

function confirmDelete() {
    // TODO: gọi API backend xóa account
    showSuccessMessage(`Account ${deleteAccountUsername} has been deleted successfully`);
    closeModal('deleteConfirmModal');
    deleteAccountUsername = '';
}

function showChangePasswordModal() {
    closeModal('profileModal');
    document.getElementById('changePasswordModal').style.display = 'block';
}

function closeModal(modalId) {
    document.getElementById(modalId).style.display = 'none';
}

// Form submissions (giữ nguyên, xử lý gửi form thực tế ở backend)
document.getElementById('addAccountForm').onsubmit = function (e) {
    e.preventDefault();
    // TODO: gửi dữ liệu lên backend
    showSuccessMessage('Account has been added successfully');
    closeModal('addAccountModal');
    this.reset();
};

document.getElementById('editAccountForm').onsubmit = function (e) {
    e.preventDefault();
    // TODO: gửi dữ liệu cập nhật lên backend
    showSuccessMessage('Account has been updated successfully');
    closeModal('editAccountModal');
};

document.getElementById('changePasswordForm').onsubmit = function (e) {
    e.preventDefault();
    const formData = new FormData(e.target);
    const newPassword = formData.get('newPassword');
    const confirmPassword = formData.get('confirmPassword');

    if (newPassword !== confirmPassword) {
        alert('New password and confirm password do not match!');
        return;
    }

    // TODO: gửi đổi mật khẩu lên backend
    showSuccessMessage('Password has been changed successfully');
    closeModal('changePasswordModal');
    this.reset();
};

// Success message function
function showSuccessMessage(message) {
    const existing = document.querySelector('.success-message');
    if (existing) {
        existing.remove();
    }

    const successDiv = document.createElement('div');
    successDiv.className = 'success-message';
    successDiv.innerHTML = `
        <i class="fas fa-check-circle"></i>
        <span>${message}</span>
    `;

    document.body.insertBefore(successDiv, document.body.firstChild);

    setTimeout(() => {
        successDiv.remove();
    }, 3000);
}

// Search functionality for account
document.getElementById('accountSearch').addEventListener('input', function (e) {
    const searchTerm = e.target.value.toLowerCase();
    const rows = document.querySelectorAll('#accountTableBody tr');

    rows.forEach(row => {
        const username = row.cells[0].textContent.toLowerCase();
        const fullName = row.cells[1].textContent.toLowerCase();

        if (username.includes(searchTerm) || fullName.includes(searchTerm)) {
            row.style.display = '';
        } else {
            row.style.display = 'none';
        }
    });
});

// Tương tự bạn có thể giữ nguyên các hàm show modal, edit, delete, search cho staff, book, supplier, promotion,... chỉ xóa phần gán dữ liệu mẫu, thêm comment TODO để bạn tiện bổ sung dữ liệu từ backend

// Initialize charts (bạn gọi sau khi trang load)
document.addEventListener('DOMContentLoaded', function () {
    initRevenueChart();
    // Bạn cũng có thể thêm initIncomeChart() tương tự khi có dữ liệu
});

// Close modals when clicking outside
window.onclick = function (event) {
    const modals = document.querySelectorAll('.modal');
    modals.forEach(modal => {
        if (event.target == modal) {
            modal.style.display = 'none';
        }
    });
}
