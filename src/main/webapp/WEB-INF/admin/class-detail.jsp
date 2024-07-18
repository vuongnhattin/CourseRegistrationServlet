<%--
  Created by IntelliJ IDEA.
  User: tinvu
  Date: 7/14/2024
  Time: 11:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container mt-3 p-0">
    <a href="/admin/class">Quay về lớp học</a>
</div>

<mytag:area header_="Lớp: ${className}">
    <mytag:table table="${table1}"></mytag:table>
</mytag:area>

<mytag:area header_="Sinh viên ngoài lớp">
    <mytag:table table="${table2}"></mytag:table>
</mytag:area>