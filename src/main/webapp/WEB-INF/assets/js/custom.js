
// custom.js - Một số tương tác nhỏ cho giao diện
document.addEventListener('DOMContentLoaded', function () {
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach(alert => {
        setTimeout(() => alert.style.display = 'none', 5000); // Ẩn sau 5s
    });
});
