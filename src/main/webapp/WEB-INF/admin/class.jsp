<%--
  Created by IntelliJ IDEA.
  User: tinvu
  Date: 7/14/2024
  Time: 5:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<mytag:area header_="Các lớp học">
    <mytag:table table="${table}"></mytag:table>
</mytag:area>

