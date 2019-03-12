<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.netcracker.edu.Person" %>
<%@ page import="java.util.ArrayList" %>
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
<% Person person = (Person) request.getAttribute("Person");%>
<%ArrayList<String> ways = (ArrayList<String>) request.getAttribute("ways");%>
    <h1 align="center"> Данные Person'a </h1>

<br>
<div class="container">
<div class="btn-group btn-group-lg" role="group">
    <div class="btn-group" role="group">
        <a class="btn btn-default btn-lg" role="button"
           href="/artifact/SavePersonServlet?format=xml&id=<%=person.getId()%>&name=<%=person.getName()%>&way=<%=person.getWay()%>&usd=<%=person.getUSD()%>">
            Сохранить в XML</a>
    </div>
    <div class="btn-group" role="group">
        <a class="btn btn-default btn-lg" role="button"
           href="/artifact/SavePersonServlet?format=csv&id=<%=person.getId()%>&name=<%=person.getName()%>&way=<%=person.getWay()%>&usd=<%=person.getUSD()%>">
                Сохранить в CSV</a>
    </div>
    <div class="btn-group" role="group">
        <a class="btn btn-default btn-lg" role="button"
           href="/artifact/SavePersonServlet?format=json&id=<%=person.getId()%>&name=<%=person.getName()%>&way=<%=person.getWay()%>&usd=<%=person.getUSD()%>">
                Сохранить в JSON</a>
    </div>
</div>
</div>

<br><br>

<div class="container">
    <form action="/artifact/UpdatePersonServlet" method="post">
    <div class="form-group row">
        <label for="id" class="col-md-1 col-form-label">ID:</label>
        <div class="col-md-7">
            <input class="form-control" type="text" value="<%=person.getId()%>" id="id" name="id" readonly/>
        </div>
    </div>
        <br>
    <div class="form-group row">
        <label for="name" class="col-md-1 col-form-label">Name:</label>
        <div class="col-md-7">
            <input class="form-control" type="text" value="<%=person.getName()%>" id="name" name="name">
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
                    <option selected value="<%=person.getWay()%>"><%=person.getWay()+" (Старое значение)"%></option>--%>
                    <%for (String way: ways){%>
                    <option value="<%=way%>"><%=way%></option><%}%>
                </select>
                </div>
            </div>
        </fieldset>
    </div>

    <div class="form-group row">
        <label for="USD" class="col-md-1 col-form-label">USD:</label>
        <div class="col-md-7">
            <input class="form-control" type="number" value="<%=person.getUSD()%>" min="0" max="2000000000" id="USD" name="usd">
        </div>
    </div>
        <button type="submit" class="btn btn-primary">Изменить</button>
    </form>
</div>

<div class="container">
    <form action="/artifact/DeletePersonServlet" method="post">
        <input type="hidden" name="id" value="<%=person.getId()%>">
        <button type="submit" class="btn btn-danger">Удалить</button>
    </form>
</div>
<div class="btn-group btn-group-lg" role="group">
    <div class="btn-group" role="group">
        <a class="btn btn-default btn-lg" onclick="history.back();">Вернуться</a>
    </div>
</div>
</body>
</html>