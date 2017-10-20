<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="ru.spospekhov.notesapp.model.Status" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Менеджер зематок</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style type="text/css">
        .myrow-container {
            margin: 20px;
        }

        .btn {
            padding: 2px 2px;
            width: 7em;
            height: 2em;
            background-color: rgba(33, 77, 25, 0.86);
            color: #f1f1f1;
            border-radius: 0;
            transition: .2s;
        }

        .btn:hover, .btn:focus {
            border: 1px solid #214d19;
            background-color: #fff;
            color: #000;
        }

        a.aEdit:link, a.aDelete:link {
            color: #a83016;
        }

        a.aEdit:visited, a.aDelete:visited {
            color: #4f9457;
        }

        a.aEdit:hover, a.aDelete:hover {
            color: #60a870;
        }

        a.aEdit:active, a.aDelete:active {
            color: #ded728;
        }

        a.aCreateNote:link, a.aSortNote:link {
            color: #000000;
        }

        a.aCreateNote:visited, .aSortNote:visited {
            color: #000000;
        }

        a.aCreateNote:hover, .aSortNote:hover {
            color: #cc353c;
        }

        .panel-footer a {
            font-size: 1.2em;
        }

        .panel-footer a:link {
            color: #d1cbbc;
        }

        .panel-footer a:visited {
            color: #c4bba5;
        }

        .panel-footer a:hover {
            color: #a0cc95;
        }

        .panel-footer a:active {
            color: #52de2d;
        }
    </style>
</head>
<body class=".container-fluid" style="background-color:whitesmoke">
<div class="container myrow-container">
    <div class="panel-body">
        <h2>Менеджер записей</h2>
    </div>

    <div class="panel">
        <div class="panel-heading" style="background-color:#607871">
            <form:form method="post" action="add" commandName="note">
                <table>
                    <tr>
                        <td><form:label path="text">
                            Текст
                        </form:label></td>
                        <td><form:input path="text"/></td>
                        <td colspan="2"><input type="submit" value="Добавить запись"/></td>
                    </tr>
                </table>
            </form:form>

            <div class="panel-heading" style="background-color:#607871">
                <h3 class="panel-title ">
                    <div align="left"><a class="aCreateNote" href="createNote">Создать новую заметку</a></div>
                </h3>
            </div>
        </div>
        <div class="panel-body">
            <h3>Заметки</h3>
            <form action="filterNote">
                <div class="row">
                    <div class="col-md-2">Фильтровать по статусу:</div>
                    <select class="col-md-2" id="filterNote" name="filterNote">
                        <c:if test="${savedStatus != null}">
                            <c:forEach items="<%=Status.values()%>" var="status">
                                <c:if test="${savedStatus == status.name()}">
                                    <option value="${status.name()}">${status.getName()}</option>
                                </c:if>
                            </c:forEach>
                            <c:forEach items="<%=Status.values()%>" var="status">
                                <c:if test="${savedStatus != status}">
                                    <option value="${status.name()}">${status.getName()}</option>
                                </c:if>
                            </c:forEach>
                        </c:if>
                        <c:if test="${savedStatus == null}">
                            <c:forEach items="<%=Status.values()%>" var="status">
                                <option value="${status.name()}">${status.getName()}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                    <div class="col-md-2"><input class="btn btn-xs" type='submit' value='Фильтровать'/></div>
                </div>
            </form>
            <c:if test="${empty noteList}">
                <h3>Заметок пока что нет.</h3>
            </c:if>
            <c:if test="${!empty noteList}">
                <table class="data table-hover table-bordered">
                    <thead style="background-color: rgba(45,77,29,0.79);">
                    <tr>
                        <th>Текст</th>
                        <th>Статус</th>

                        <th>
                            <a class="aSortNote" href="sortNote" >
                                Дата создания
                            </a>
                        </th>

                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${noteList}" var="note">
                        <tr>
                            <th>${note.text}</th>
                            <th>${Status.valueOf(note.status).getName()}</th>
                            <th>${note.createdDate}</th>
                            <th><a class="aEdit" href="editNote?id=<c:out value='${note.id}'/>">Изменить</a></th>
                            <th><a class="aDelete" href="delete/${note.id}">Удалить</a></th>
                            <th>
                                <c:if test="${note.status != 'COMPLETE'}">
                                <a class="aEdit" href="complete/${note.id}">Выполнить</a></th>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
    <div align="center" class="panel-footer" style="background-color:#607871" id="pagination">
        <c:url value="/" var="prev">
            <c:param name="page" value="${page-1}"/>
        </c:url>
        <c:if test="${page > 1}">
            <a href="<c:out value="${prev}" />" class="pn prev">Предыдущая</a>
        </c:if>

        <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
            <c:choose>
                <c:when test="${page == i.index}">
                    <span>${i.index}</span>
                </c:when>
                <c:otherwise>
                    <c:url value="/" var="url">
                        <c:param name="page" value="${i.index}"/>
                    </c:url>
                    <a href='<c:out value="${url}" />'>${i.index}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:url value="/" var="next">
            <c:param name="page" value="${page + 1}"/>
        </c:url>
        <c:if test="${page + 1 <= maxPages}">
            <a href='<c:out value="${next}" />' class="pn next">Следующая</a>
        </c:if>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</div>
</body>
</html>