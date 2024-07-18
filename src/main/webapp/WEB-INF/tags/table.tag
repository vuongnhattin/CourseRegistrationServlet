<%@ tag import="com.google.gson.Gson" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="table" type="utils.HTMLTable.Table" required="true" %>
<%@ tag language="java" pageEncoding="UTF-8" %>

<%
    String ui = "id_" + System.currentTimeMillis();
%>

<c:set var="ui" scope="page" value="<%=ui%>">
</c:set>




<div class="d-grid gap-2 d-md-flex justify-content-md-center">
    <c:forEach items="${table.actions}" var="action">
        <c:choose>
            <c:when test='${action.actionType.equals("get")}'>
                <form action="${action.url}" method="${action.method.equals('get') ? 'get' : 'post'}">
                    <c:if test="${!action.method.equals('get')}">
                        <input type="hidden" name="_method" value="${action.method}">
                    </c:if>
                    <input type="hidden" name="${table.fieldVariables[0]}" id="_${action.id}-${table.fieldVariables[0]}-hidden">
                    <button type="submit" class="btn ${action.buttonType} ${action.needSelect == true ? 'disabled' : ''}" id="_${action.id}Button">${action.buttonName}</button>
                </form>
            </c:when>
            <c:otherwise>
                <button id="_${action.id}Button" class="btn ${action.buttonType} ${action.needSelect == true ? "disabled" : ""}"
                        data-bs-toggle="modal" data-bs-target="#_${action.id}Modal">
                        ${action.buttonName}
                </button>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>
<table id="table_${ui}" class="table table-bordered">
    <thead class="table-light">
    <c:forEach items="${table.fieldNames}" var="name">
        <th>${name}</th>
    </c:forEach>
    </thead>
    <tbody>
    <c:forEach items="${table.data}" var="i">
        <tr>
            <c:forEach items="${i.row}" var="j">
                <td>${j}</td>
            </c:forEach>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:forEach items="${table.actions}" var="action" varStatus="i">
    <c:if test='${action.actionType.equals("add")}'>
        <%--Add modal--%>
        <mytag:modal id="_${action.id}Modal" header_="${action.modalHeader}" body_="${action.modalBody}" method="post"
                     action="${action.url}">
            <input type="hidden" name="_method" value="${action.method}">
            <c:forEach items="${table.fieldNames}" var="fieldName" varStatus="j">
                <div class="mb-3">
                    <mytag:formField id="_${action.id}-${table.fieldVariables[j.index]}"
                                     fieldType="${table.fieldTypes[j.index]}" name="${table.fieldVariables[j.index]}"
                                     label="${fieldName}" options="${table.fieldOptions[j.index]}" disabled="${table.fieldDisabledAdd[j.index]}">

                    </mytag:formField>
                </div>
            </c:forEach>
        </mytag:modal>
    </c:if>

    <c:if test='${action.actionType.equals("update")}'>
        <%--Update modal--%>
        <mytag:modal id="_${action.id}Modal" header_="${action.modalHeader}" body_="${action.modalBody}" method="post"
                     action="${action.url}">
            <input type="hidden" name="_method" value="${action.method}">
            <c:forEach items="${table.fieldNames}" var="fieldName" varStatus="j">
                <c:if test="${table.fieldDisabledUpdate[j.index]}">
                    <input type="hidden" name="${table.fieldVariables[j.index]}" id="_${action.id}-${table.fieldVariables[j.index]}-hidden">
                </c:if>
                <div class="mb-3">
                    <mytag:formField id="_${action.id}-${table.fieldVariables[j.index]}"
                                     fieldType="${table.fieldTypes[j.index]}" name="${table.fieldVariables[j.index]}"
                                     label="${fieldName}" options="${table.fieldOptions[j.index]}"
                                     disabled="${table.fieldDisabledUpdate[j.index]}">

                    </mytag:formField>
                </div>
            </c:forEach>
        </mytag:modal>
    </c:if>

    <c:if test='${action.actionType.equals("delete")}'>
        <%--Delete modal--%>
        <mytag:modalNoFooter id="_${action.id}Modal" header_="${action.modalHeader}" body_="${action.modalBody}"
                             method="post" action="${action.url}">
            <input type="hidden" name="_method" value="${action.method}">
            <input type="hidden" id="_${action.id}-${table.fieldVariables[0]}-hidden" name="${table.fieldVariables[0]}">
        </mytag:modalNoFooter>
    </c:if>
</c:forEach>
<script>
    const table_${ui} = new DataTable("#table_${ui}");

    table_${ui}.on('click', 'tbody tr', (e) => {
        const actions = <%= new Gson().toJson(table.getActions())%>;
        const fieldVariables = <%= new Gson().toJson(table.getFieldVariables())%>;
        const fieldDisabledUpdate = <%= new Gson().toJson(table.getFieldDisabledUpdate())%>;

        let classList = e.currentTarget.classList;

        if (classList.contains('selected')) {
            for (let action of actions) {
                if (action['needSelect'] === true) {
                    $('#_' + action['id'] + 'Button').addClass("disabled")
                }
            }

            classList.remove('selected');
        } else {
            for (let action of actions) {
                if (action['needSelect'] === true) {
                    $('#_' + action['id'] + 'Button').removeClass("disabled")
                }
            }

            table_${ui}.rows('.selected').nodes().each((row) => row.classList.remove('selected'));
            classList.add('selected');

            const data = table_${ui}.row('.selected').data();

            for (let action of actions) {
                if (action['actionType'] === 'update') {
                    for (let i in fieldVariables) {
                        $('#_' + action['id'] + '-' + fieldVariables[i]).val(data[i])
                        if (fieldDisabledUpdate[i]) {
                            $('#_' + action['id'] + '-' + fieldVariables[i] + '-hidden').val(data[i])
                        }
                    }
                }
                if (action['actionType'] === 'delete' || action['actionType'] === 'get') {
                    $('#_' + action['id'] + '-' + fieldVariables[0] + '-hidden').val(data[0])
                }
            }
        }
    })
</script>
