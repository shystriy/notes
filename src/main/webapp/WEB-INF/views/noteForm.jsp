<%--
  Created by IntelliJ IDEA.
  User: spospekhov
  Date: 19.10.2017
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="ru.spospekhov.notesapp.model.Status" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Именить заметку</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style type="text/css">
        .myrow-container {
            margin: 20px;
        }

        .panel-title {
            color: #d1cbbc;
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
    </style>
</head>
<body class=".container-fluid" style="background-color:whitesmoke">
<div class="container myrow-container">
    <div class="panel panel-success">
        <div class="panel-heading" style="background-color:#607871">
            <h3 class="panel-title" style="color: #d1cbbc">
                Заметка:
            </h3>
        </div>
        <div class="panel-body">
            <form:form id="NoteEditForm" cssClass="form-horizontal" modelAttribute="note" method="post" action="saveNote">



                <div class="form-group">
                    <div class="control-label col-xs-3"><form:label path="text">Текст</form:label></div>
                    <div class="col-xs-6">
                        <form:hidden path="id" value="${noteObject.id}"/>
                        <form:input cssClass="form-control" path="text" value="${noteObject.text}"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="control-label col-xs-3">
                        <form:label path="status">Статус</form:label>
                    </div>
                    <select class="col-xs-6" id="status" name="status">
                        <c:if test="${noteObject.status != null}">

                            <c:if test="${noteObject.status == 'COMPLETE'}">
                                <option path="status" value="COMPLETE">Выполнена</option>
                                <option path="status" value="NOT_COMPLETE">Не выполнена</option>
                            </c:if>
                            <c:if test="${noteObject.status == 'NOT_COMPLETE'}">
                                <option path="status" value="NOT_COMPLETE">Не выполнена</option>
                                <option path="status" value="COMPLETE">Выполнена</option>
                            </c:if>
                        </c:if>
                        <c:if test="${noteObject.status == null}">
                            <option value="COMPLETE">Выполнена</option>
                            <option value="NOT_COMPLETE">Не выполнена</option>
                        </c:if>
                    </select>
                </div>

                <div class="form-group">
                    <div class="control-label col-xs-3"><form:label path="createdDate">Дата создания</form:label></div>
                    <div class="col-xs-6"><form:label
                            path="createdDate">${noteObject.createdDate}</form:label></div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <div class="col-xs-4">
                        </div>
                        <div class="col-xs-4">
                            <input type="submit" id="saveNote" class="btn btn-primary" value="Сохранить"/>
                        </div>
                        <div class="col-xs-4">
                        </div>
                    </div>
                </div>

            </form:form>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>
