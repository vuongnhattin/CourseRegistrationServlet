<%--
  Created by IntelliJ IDEA.
  User: tinvu
  Date: 7/15/2024
  Time: 10:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<mytag:area header_="Thời gian đăng ký học phần">
    <mytag:table table="${registerTimeTable}"></mytag:table>
</mytag:area>

<mytag:area header_="Các học phần mở cho học kỳ hiện tại (${currentSemester.name} - Năm học ${currentSemester.year})">
    <mytag:table table="${courseTable}"></mytag:table>
</mytag:area>
