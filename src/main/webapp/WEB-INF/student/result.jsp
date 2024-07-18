<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: tinvu
  Date: 7/17/2024
  Time: 3:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<mytag:area header_="Lịch sử đăng ký học phần">
    <form action="/student/result" method="get">
        <div class="container p-0 mb-3">
            <div class="row justify-content-center">
                <div class="col-auto">
                    <label for="semesterYear" class="col-form-label">Năm học:</label>
                </div>
                <div class="col-auto">
                    <input type="text" class="form-control" name="semesterYear" id="semesterYear" value="${semesterYear}">
                </div>

                <div class="col-auto">
                    <label for="semesterName" class="col-form-label">Học kỳ:</label>
                </div>
                <div class="col-auto">
                    <select class="form-select" id="semesterName" name="semesterName">
                        <c:set var="options" value='<%= List.of("HK1", "HK2", "HK3")%>'></c:set>
                        <c:forEach var="option" items="${options}">
                            <option value="${option}" <c:if test="${option == semesterName}">selected</c:if>>${option}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-auto">
                    <button type="submit" class="form-control btn btn-primary">Xem kết quả</button>
                </div>
            </div>
        </div>
    </form>
    <mytag:table table="${table}"></mytag:table>
</mytag:area>
