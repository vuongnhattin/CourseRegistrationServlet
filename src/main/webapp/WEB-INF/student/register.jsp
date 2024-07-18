<%--
  Created by IntelliJ IDEA.
  User: tinvu
  Date: 7/17/2024
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${!isRegisterTime}">
    <div class="h3 container mt-3">Hiện chưa phải là thời gian để đăng ký học phần</div>
</c:if>



<c:if test="${isRegisterTime}">
    <jsp:useBean id="currentSemester" scope="request" type="models.Semester"></jsp:useBean>
    <jsp:useBean id="registerTime" scope="request" type="models.RegisterTime"></jsp:useBean>
    <mytag:area header_="Thông tin kỳ đăng ký học phần">
        <p>Học kỳ: <strong>${currentSemester.name}</strong> - Năm học <strong>${currentSemester.year}</strong></p>
        <p>Thời gian bắt đầu đăng ký: <strong>${registerTime.startTime}</strong></p>
        <p>Thời gian kết thúc đăng ký: <strong>${registerTime.endTime}</strong></p>
        <span data-feather="help-circle"></span>
        <span class="p-0"> <strong>Lưu ý:</strong> bạn chỉ có thể đăng ký tối đa 5 học phần</span>
    </mytag:area>

    <mytag:area header_="Các học phần đã đăng ký">
        <mytag:table table="${resultTable}"></mytag:table>
    </mytag:area>

    <mytag:area header_="Các học phần đang mở">
        <mytag:table table="${courseTable}"></mytag:table>
    </mytag:area>
</c:if>
