<%--
  Created by IntelliJ IDEA.
  User: Penguin
  Date: 2/1/2019
  Time: 12:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить персону</title>
</head>
<body>
<h2 align="center">Добавить персону</h2>
<hr align="center" width="500" size="2" color="#ff0000"/>
<p>Укажите данные персоны</p>

<form action="addPerson" method="post">
    ID: <input name="id"/>
    <br><br>
    Name: <input name="name"/>
    <br><br>
    Way: <input name="way"/>
    <br><br>
    USD: <input name="usd"/>
    <br><br>
    <input type="submit" value="Подтвердить">
</form>

<form action="index.jsp">
    <button type="submit">На главную</button>
</form>
</body>
</html>
