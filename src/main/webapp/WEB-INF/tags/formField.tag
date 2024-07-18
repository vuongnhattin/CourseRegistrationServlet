<%--
  Created by IntelliJ IDEA.
  User: tinvu
  Date: 7/12/2024
  Time: 12:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="id" type="java.lang.String" required="true" %>
<%@ attribute name="fieldType" type="java.lang.String" required="true" %>
<%@ attribute name="name" type="java.lang.String" required="true" %>
<%@ attribute name="label" type="java.lang.String" required="true" %>
<%@ attribute name="options" type="java.util.List" required="false" %>
<%@ attribute name="value" type="java.lang.String" required="false" %>
<%@ attribute name="disabled" type="java.lang.Boolean" required="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<label for="${id}" class="form-label">${label}</label>

<%
    Boolean isDisabled = (disabled != null) ? disabled : false;
%>

<c:choose>
    <c:when test="${fieldType == 'text'}">
        <input type="text" class="form-control" id="${id}" name="${name}" value="${value}" <%= isDisabled ? "disabled" : "" %>/>
    </c:when>
    <c:when test="${fieldType == 'date'}">
        <input type="date" class="form-control" id="${id}" name="${name}" value="${value} <%= isDisabled ? "disabled" : "" %>"/>
    </c:when>
    <c:when test="${fieldType == 'select'}">
        <select class="form-select" id="${id}" name="${name}" <%= isDisabled ? "disabled" : "" %>>
            <c:forEach var="option" items="${options}">
                <option value="${option}" <c:if test="${option == value}">selected</c:if>>${option}</option>
            </c:forEach>
        </select>
    </c:when>
    <c:otherwise>
        <input type="${fieldType}" class="form-control" id="${id}" name="${name}" value="${value}"/>
    </c:otherwise>
</c:choose>

