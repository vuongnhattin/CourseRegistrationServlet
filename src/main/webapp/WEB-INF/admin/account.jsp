<%--
  Created by IntelliJ IDEA.
  User: tinvu
  Date: 7/9/2024
  Time: 12:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<mytag:area header_="Tài khoản giáo vụ">
    <mytag:table table="${adminTable}"></mytag:table>
</mytag:area>


<mytag:area header_="Tài khoản sinh viên">
    <mytag:table table="${studentTable}"></mytag:table>
</mytag:area>
