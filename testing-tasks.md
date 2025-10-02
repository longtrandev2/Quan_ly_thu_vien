# Kiểm thử cấu hình Security và Authentication

## Tổng quan
File này liệt kê các nhiệm vụ kiểm thử cho các tính năng bảo mật vừa được triển khai, bao gồm login, phân quyền, redirect sau login và xử lý lỗi truy cập.

## Nhiệm vụ kiểm thử

### 1. Kiểm thử đăng nhập (Login)
- [ ] Đăng nhập với tài khoản hợp lệ (email/password đúng)
- [ ] Đăng nhập với email không tồn tại
- [ ] Đăng nhập với mật khẩu sai
- [ ] Đăng nhập với email trống
- [ ] Đăng nhập với mật khẩu trống
- [ ] Kiểm tra redirect sau login thành công (về trang chủ "/")

### 2. Kiểm thử phân quyền (Authorization)
- [ ] Truy cập trang công khai ("/", "/login", "/register") khi chưa đăng nhập
- [ ] Truy cập trang admin ("/admin/**") với tài khoản không có quyền ADMIN
- [ ] Truy cập trang user ("/user/**") với tài khoản không có quyền USER hoặc ADMIN
- [ ] Truy cập trang product ("/product/**") với tài khoản không có quyền ADMIN
- [ ] Kiểm tra rằng tài nguyên tĩnh (CSS, JS, images) được truy cập mà không cần đăng nhập

### 3. Kiểm thử redirect sau đăng nhập
- [ ] Đăng nhập với tài khoản có role ADMIN → redirect về "/"
- [ ] Đăng nhập với tài khoản có role USER → redirect về "/"
- [ ] Đăng nhập với tài khoản không có role → redirect về "/"

### 4. Kiểm thử trang Access Denied
- [ ] Truy cập URL không được phép → hiển thị trang "/access-denied"
- [ ] Kiểm tra nội dung trang access-denied (tiêu đề, thông báo lỗi, nút quay về)
- [ ] Kiểm tra rằng trang access-denied có thể truy cập được

### 5. Kiểm thử đăng xuất (Logout)
- [ ] Đăng xuất thành công → redirect về "/login?logout"
- [ ] Kiểm tra session bị xóa sau logout
- [ ] Kiểm tra không thể truy cập trang bảo vệ sau logout

### 6. Kiểm thử Method Security (nếu có)
- [ ] Kiểm tra các method được bảo vệ bằng @PreAuthorize
- [ ] Kiểm tra các method được bảo vệ bằng @Secured
- [ ] Thử truy cập method không được phép → ném exception

## Chuẩn bị dữ liệu kiểm thử

### Tạo tài khoản test
1. Tạo tài khoản ADMIN:
   - Email: admin@test.com
   - Password: admin123
   - Role: ADMIN

2. Tạo tài khoản USER:
   - Email: user@test.com
   - Password: user123
   - Role: USER

3. Tạo tài khoản không có role:
   - Email: guest@test.com
   - Password: guest123
   - Role: (không có)

### Chạy ứng dụng
```bash
mvn spring-boot:run
```

### Truy cập ứng dụng
- URL: http://localhost:8080
- Database: Đảm bảo tables đã được tạo và dữ liệu test đã được insert

## Ghi chú
- Đảm bảo Spring Security được cấu hình đúng trong `CustomWebSecurityConfigurer`
- Kiểm tra console log để xem các lỗi authentication/authorization
- Sử dụng browser developer tools để kiểm tra HTTP status codes (401, 403, etc.)
