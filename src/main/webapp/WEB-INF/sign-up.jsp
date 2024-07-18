<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tinvu
  Date: 7/6/2024
  Time: 9:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</head>
<body>
<!-- Login 13 - Bootstrap Brain Component -->
<section class="bg-light py-3 py-md-5">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-sm-10 col-md-8 col-lg-6 col-xl-5 col-xxl-4">
                <div class="card border border-light-subtle rounded-3 shadow-sm">
                    <div class="card-body p-3 p-md-4 p-xl-5">
                        <div class="text-center mb-3">
                            <a href="#!">
                                <img src="<c:url value="/assets/images/khtn-logo.png" />" width="150" height="150"/>
                            </a>
                        </div>
                        <h2 class="fs-6 fw-normal text-center text-secondary mb-4">Đăng kí tài khoản</h2>
                        <form action="/sign-up" method="post">
                            <div class="row gy-2 overflow-hidden">
                                <div class="col-12">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" name="username" id="username"
                                               placeholder=username" required>
                                        <label for="username" class="form-label">Tên người dùng</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" name="fullName" id="fullName"
                                               placeholder=fullName" required>
                                        <label for="fullName" class="form-label">Họ và tên</label>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-floating mb-3">
                                        <input type="password" class="form-control" name="password" id="password"
                                               value="" placeholder="Password" required>
                                        <label for="password" class="form-label">Mật khẩu</label>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-floating mb-3">
                                        <input type="password" class="form-control" name="re-password" id="re-password"
                                               value="" placeholder="Password" required>
                                        <label for="re-password" class="form-label">Nhập lại mật khẩu</label>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-floating">
                                        <select class="form-select" id="sex" name="sex" aria-label="Floating label select example">
                                            <option value="Nam">Nam</option>
                                            <option value="Nữ">Nữ</option>
                                            <option value="Khác">Khác</option>
                                        </select>
                                        <label for="birthday">Giới tính</label>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-floating mb-3">
                                        <input type="date" class="form-control" name="birthday" id="birthday" value=""
                                               placeholder="Birthday" required>
                                        <label for="birthday" class="form-label">Ngày sinh</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-floating">
                                        <select class="form-select" id="department" name="department" aria-label="Floating label select example">
                                            <option value="Toán tin">Toán tin</option>
                                            <option value="Vật lý">Vật lý</option>
                                            <option value="Sinh Học">Sinh Học</option>
                                            <option value="Công nghệ thông tin">Công nghệ thông tin</option>
                                            <option value="Hoá học">Hoá học</option>
                                        </select>
                                        <label for="department">Khoa</label>
                                    </div>
                                </div>
                                <div class="text-danger">${error}</div>
                                <br>
                                <div class="col-12">
                                    <div class="d-grid my-3">
                                        <button class="btn btn-primary btn-lg" type="submit">Đăng ký</button>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <p class="m-0 text-secondary text-center">Quay lại trang <a href="/login" class="link-primary text-decoration-none">Đăng nhập</a></p>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>