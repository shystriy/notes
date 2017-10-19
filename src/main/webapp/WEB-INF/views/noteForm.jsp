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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Именить заметку</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style type="text/css">
        .myrow-container{
            margin: 20px;
        }
        .panel-title{
            color: #d1cbbc;
        }
        .btn {
            padding: 2px 2px;
            width: 5em;
            height: 2em;
            background-color: #4d3a1e;
            color: #f1f1f1;
            border-radius: 0;
            transition: .2s;
        }
        .btn:hover, .btn:focus {
            border: 1px solid #4d3a1e;
            background-color: #fff;
            color: #000;
        }
    </style>
</head>
<body class=".container-fluid" style="background-color:whitesmoke">
<div class="container myrow-container">
    <div class="panel panel-success">
        <div class="panel-heading" style="background-color:#786455">
            <h3 class="panel-title" style="color: #d1cbbc">
                Заметка:
            </h3>
        </div>
        <div class="panel-body">
            <form:form id="NoteEditForm" cssClass="form-horizontal" modelAttribute="note" method="post" action="add">

                <div class="form-group">
                    <div class="control-label col-xs-3"> <form:label path="text" >Текст</form:label> </div>
                    <div class="col-xs-6">
                        <form:hidden path="id" value="${noteObject.id}"/>
                        <form:input cssClass="form-control" path="text" value="${noteObject.text}"/>
                    </div>
                </div>

                <div class="form-group">
                    <form:label path="status" cssClass="control-label col-xs-3">Статус</form:label>
                    <div class="col-xs-6">
                        <form:input cssClass="form-control" path="status" value="${noteObject.status}"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="control-label col-xs-3"><form:label path="createdDate">Дата создания</form:label></div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <div class="col-xs-4">
                        </div>
                        <div class="col-xs-4">
                            <input type="submit" id="saveNote" class="btn btn-primary" value="Сохранить" onclick="return submitNoteForm();"/>
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

<script type="text/javascript">
    function submitNoteForm() {
        var text = $('#text').val().trim();
        var status = $('#status').val();
        if(text.length ==0) {
            alert('Введите текст заметки!');
            $('#text').focus();
            return false;
        }
        return true;
    };
</script>
</body>
</html>
