<%--
  Created by IntelliJ IDEA.
  User: Penguin
  Date: 2/1/2019
  Time: 12:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Список персон</title>
</head>
<body>
<h2 align="center">Существующие персоны</h2>
<hr align="center" width="500" size="2" color="#ff0000"/>
<div>Введите id Персоны.</div>

<form action="ShowSpecificPerson" method="post">
    ID: <input name="id"/>
    <br><br>
    <%--Name: <input name="name"/>--%>
    <%--<br><br>--%>
    <%--Way: <input name="way"/>--%>
    <%--<br><br>--%>
    <%--USD: <input name="usd"/>--%>
    <%--<br><br>--%>
    <input type="submit" value="Подтвердить">
</form>


<form action="index.jsp">
    <button type="submit">На главную</button>
</form>

</body>
</html>
