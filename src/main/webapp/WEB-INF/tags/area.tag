<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="header_" type="java.lang.String" required="true" %>

<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
    <h1 class="h3">${header_}</h1>
</div>
<div class="mb-4 p-4 border rounded-3 bg-light">
    <jsp:doBody/>
</div>