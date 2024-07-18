<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tinvu
  Date: 7/6/2024
  Time: 5:48 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h3">Thay đổi thông tin tài khoản, mật khẩu</h1>
</div>
<div class="mb-4 p-4 bg-light border rounded-3">
    <div class="container">
        <div class="row">
            <div class="col-6">
                <form method="post" action="/admin/me/update">
                    <fieldset>
                        <%--            <legend>Thông tin tài khoản</legend>--%>
                        <div class="mb-3">
                            <label for="username" class="form-label">Tên đăng nhập</label>
                            <input disabled type="text" value="${me.username}" id="username"
                                   class="form-control">
                        </div>
                            <input type="hidden" name="username" value="${me.username}">
                        <div class="mb-3">
                            <label for="name" class="form-label">Họ và tên</label>
                            <input type="text" name="name" value="${me.name}" id="name" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="birthday" class="form-label">Ngày sinh</label>
                            <input type="date" name="birthday" value="${me.birthday}" id="birthday"
                                   class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="gender" class="form-label">Giới tính</label>
                            <select id="gender" class="form-select" name="gender">
                                <option ${me.gender.equals("Nam") ? "selected" : ""}>Nam</option>
                                <option ${me.gender.equals("Nữ") ? "selected" : ""}>Nữ</option>
                                <option ${me.gender.equals("Khác") ? "selected" : ""}>Khác</option>
                            </select>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Thay đổi</button>
                        </div>
                    </fieldset>
                </form>
            </div>
            <div class="col-6">
                <form action="/admin/me/change-password" method="post">
                    <div class="mb-3">
                        <label for="old-password" class="form-label">Mật khẩu cũ</label>
                        <input type="password" name="old-password" id="old-password" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="new-password" class="form-label">Mật khẩu mới</label>
                        <input type="password" name="new-password" id="new-password" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="renew-password" class="form-label">Nhập lại mật khẩu mới</label>
                        <input type="password" name="renew-password" id="renew-password" class="form-control">
                    </div>
                    <c:if test="${change_error != null}">
                        <div class="text-danger">${change_error}</div><br>
                    </c:if>
                    <c:if test="${change_success != null}">
                        <div class="text-success">${change_success}</div><br>
                    </c:if>
                    <div class="text-center">
                        <input type="submit" class="btn btn-primary" value="Đổi mật khẩu"></input>
                    </div>
                </form>
                <br>
            </div>
        </div>
    </div>
    <br>
</div>


<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <div class="h3">Đặt lại mật khẩu</div>
</div>
<div class="mb-4 p-4 bg-light border rounded-3">
    <p class="">Mật khẩu mới sẽ là: <strong>123</strong></p>
    <c:if test="${reset_success != null}">
        <div class="text-success">${reset_success}</div><br>
    </c:if>
    <form action="/admin/me/reset-password" method="post">
        <button type="submit" class="btn btn-danger">Đặt lại mật khẩu</button>
    </form>
</div>
