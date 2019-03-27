<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <title>Страница действия над Person</title>
</head>
<body>
    <h1 align="center"> Данные Person'a </h1>

<br>
<div class="container">
<div class="btn-group btn-group-lg" role="group">
    <div class="btn-group" role="group">
        <a class="btn btn-default btn-lg" role="button"
           href="${pageContext.request.contextPath}/savePerson?format=xml&id=${person.id}&name=${person.name}&way=${person.way}&USD=${person.USD}">
            Сохранить в XML</a>
    </div>
    <div class="btn-group" role="group">
        <a class="btn btn-default btn-lg" role="button"
           href="${pageContext.request.contextPath}/savePerson?format=csv&id=${person.id}&name=${person.name}&way=${person.way}&USD=${person.USD}">
                Сохранить в CSV</a>
    </div>
    <div class="btn-group" role="group">
        <a class="btn btn-default btn-lg" role="button"
           href="${pageContext.request.contextPath}/savePerson?format=json&id=${person.id}&name=${person.name}&way=${person.way}&USD=${person.USD}">
                Сохранить в JSON</a>
    </div>
</div>
</div>

<br><br>

<div class="container">
    <form action="/updatePerson" method="get">
    <div class="form-group row">
        <label for="id" class="col-md-1 col-form-label">ID:</label>
        <div class="col-md-7">
            <input class="form-control" type="text" value="${person.id}" id="id" name="id" readonly/>
        </div>
    </div>
        <br>
    <div class="form-group row">
        <label for="name" class="col-md-1 col-form-label">Name:</label>
        <div class="col-md-7">
            <input class="form-control" type="text" value="${person.name}" id="name" name="name">
        </div>
    </div>
        <br>
    <div class="form-group row">
        <label for="way" class="col-md-1 col-form-label">Way:</label>
        <fieldset class="form-group">
            <div class="col">
                <div class="form-check col-sm-1">
                    <input class="form-check-input" type="radio" name="radio" value="option1" checked>
                </div>
                <div class="col-md-3">
                    <input class="form-control" type="text" id="way" name="way" value=" ">
                </div>
            </div>
            <div class="col">
                <div class="form-check col-sm-1">
                    <input class="form-check-input" type="radio" name="radio" value="option2">
                </div>
                <div class="col-md-3">
                    <select class="form-control" id="select" name="selectWay">
                        <option selected value="${person.way}">${person.way} (Исходный)</option>
                        <c:forEach items="${ways}" var ="way">
                                <option value="${way.name}">${way.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </fieldset>
    </div>

    <div class="form-group row">
        <label for="USD" class="col-md-1 col-form-label">USD:</label>
        <div class="col-md-7">
            <input class="form-control" type="number" value="${person.USD}" min="0" max="2000000000" id="USD" name="USD">
        </div>
    </div>
        <button type="submit" class="btn btn-primary">Изменить</button>
    </form>
</div>
<div class="btn-group" role="group">
    <a class="btn btn-default btn-lg" role="button"
       href="${pageContext.request.contextPath}/deletePerson?id=${person.id}">Удалить</a>
</div>
<div class="btn-group btn-group-lg" role="group">
    <div class="btn-group" role="group">
        <a class="btn btn-default btn-lg" onclick="history.back();">Вернуться</a>
    </div>
</div>
</body>
</html>