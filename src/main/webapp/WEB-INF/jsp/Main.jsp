<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <title>Список Person</title>
</head>
<body>
    <h1 align="center">Список всех имеющихся Person</h1>
    <h2 align="center">Доступно изменение/удаление/сохранение Person'a по клику на ID</h2>
    <br>

    <div class="btn-group btn-group-lg " role="group">
        <div class="btn-group" role="group">
            <a class="btn btn-primary btn-lg" role="button" href="/pageToAddPerson"> + Добавить Person'а</a>
        </div>
    </div>
    <br>

    <div class="container form-group">
        <input type="text" class="form-control pull-right" id="search" placeholder="Поиск по таблице">
    </div>
    <div class="panel panel-default">
        <table class="table table-striped" cellspacing="0" id="mytable">
            <thread>
                <tr bgcolor="#3486c6">
                    <th width="10px" height="20px"><strong>   </strong></th>
                    <th width="100px" height="20px"><strong>№</strong></th>
                    <th width="100px" height="20px"><strong>Id</strong></th>
                    <th width="150px" height="20px"><strong>Name</strong></th>
                    <th width="150px" height="20px"><strong>Way</strong></th>
                    <th width="150px" height="20px"><strong>USD</strong></th>
                </tr>
            </thread>
            <tbody>
            <c:set var="i" value="${1}"/>
            <c:forEach items="${listOfPersons}" var ="person">
                <tr><td>   </td>
                    <td>${i}</td>
                    <td><a href="${pageContext.request.contextPath}/personPage?id=${person.id}&name=${person.name}&way=${person.way}&USD=${person.USD}">${person.id}</a></td>
                    <td>${person.name}</td>
                    <td>${person.way}</td>
                    <td>${person.USD}</td>
                </tr>
                <c:set var="i" value="${i+1}"/>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <br>
    <div class="btn-group btn-group-lg" role="group">
        <div class="btn-group" role="group">
            <a class="btn btn-default btn-lg" onclick="history.back();">Вернуться</a>
        </div>
    </div>
    <script>
    $(document).ready(function(){
    $("#search").keyup(function(){
    _this = this;
    $.each($("#mytable tbody tr"), function() {
        if($(this).text().toLowerCase().indexOf($(_this).val().toLowerCase()) === -1) {
            $(this).hide();
    } else {
    $(this).show();
    }
    });
    });
    });
    </script>
</body>
</html>