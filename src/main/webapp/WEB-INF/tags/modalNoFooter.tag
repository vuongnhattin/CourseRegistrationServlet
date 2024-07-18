<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="header_" type="java.lang.String" required="true" %>
<%@ attribute name="body_" type="java.lang.String" required="true" %>
<%@ attribute name="id" type="java.lang.String" required="true" %>
<%@ attribute name="method" type="java.lang.String" required="true" %>
<%@ attribute name="action" type="java.lang.String" required="true" %>

<div class="modal fade" id="${id}" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5">${header_}</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
            </div>
            <div class="modal-body">
                <form action="${action}" method="${method}">
                    <jsp:doBody/>
                    <div class="text-center">
                        <c:if test="${body_ != null}">
                            <p>${body_}</p>
                        </c:if>
                        <button type="submit" class="btn btn-danger">Xác nhận</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>