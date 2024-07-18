<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: tinvu
  Date: 7/6/2024
  Time: 1:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<mytag:area header_="Môn học">
    <mytag:table table="${table}"></mytag:table>
</mytag:area>
