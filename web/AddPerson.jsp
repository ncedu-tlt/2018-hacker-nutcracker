<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Меню добавления Person</title>
</head>
<body>
    <h1 align="center">Введите данные</h1>
<form action="/AddPersonServlet" method="post">
        ID :<input required type = "number" min="1" max="2000000000" name = "id" /><br><br>
        NAME :<input required type = "text" name = "name"/><br><br>
        WAY :<input required type = "text" name = "way"/><br><br>
        USD :<input required type = "number" min="0" max="2000000000" name = "usd"/><br><br>
    <input type="submit" value="Сохранить"/>
    <input type="button" onclick="history.back();" value="Назад"/>
</form>
</body>
</html>
