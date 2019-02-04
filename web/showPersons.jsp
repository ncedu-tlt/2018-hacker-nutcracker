<%@ page import="java.util.ArrayList" %>
<%@ page import="program.Person" %><%--
  Created by IntelliJ IDEA.
  User: Penguin
  Date: 2/1/2019
  Time: 12:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<style type="text/css">
    TABLE {
        width: 300px;
        border-bottom: none;
    }
    TH {
        text-align: center;
    }
    TD {
        text-align: center;
        border-bottom: 1px solid red;
    }
</style>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Показать персону</title>
</head>
<body>
<h2 align="center">Список персон</h2>
<hr align="center" width="500" size="2" color="#ff0000"/>
<table align="center">
    <caption><tr><th>ID</th><th>Name</th><th>Way</th><th>Usd</th></tr></caption>
    <c:forEach var="person" items="${Persons}">
    <tr>
        <td><a href="PersonPage?id=${person.id}&name=${person.name}&way=${person.way}&usd=${person.USD}">${person.id}</a></td>
    <td>${person.name}</td>
    <td>${person.way}</td>
    <td>${person.USD}</td></tr>
    </c:forEach>
</table>

<form action="index.jsp">
    <button type="submit">На главную</button>
</form>

</body>
</html>
