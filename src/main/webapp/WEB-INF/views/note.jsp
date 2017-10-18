<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title><spring:message code="label.title" /></title>
</head>
<body>

<a href="<c:url value="/logout" />">
    Выйти из приложения
</a>

<h2>Менеджер записей</h2>

<form:form method="post" action="add" commandName="note">

    <table>
        <tr>
            <td><form:label path="text">
                Текст
            </form:label></td>
            <td><form:input path="text" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"
                                   value="Добавить запись" /></td>
        </tr>
    </table>
</form:form>

<h3><spring:message code="label.notes" /></h3>
<c:if test="${!empty noteList}">
    <table class="data">
        <tr>
            <th><spring:message code="label.text" /></th>
            <th><spring:message code="label.status" /></th>
            <th><spring:message code="label.createdDate" /></th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${noteList}" var="note">
            <tr>
                <td>${note.text}</td>
                <td>${note.status}</td>
                <td>${note.createdDate}</td>
                <td><a href="delete/${note.id}"><spring:message code="label.delete" /></a></td>
                <td><c:if test="${note.status != 'Выполнена'}">
                    <a href="complete/${note.id}">
                    <spring:message code="label.complete" /></a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>