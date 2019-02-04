<%@ page import="program.Person" %><%--
  Created by IntelliJ IDEA.
  User: Penguin
  Date: 2/3/2019
  Time: 9:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head >
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Страница персоны</title>
</head>
<body>
<% Person person = (Person) request.getAttribute("Person");%>
<h1 align="center"> Данные персоны </h1>

<form action="ChangePerson" method="post">
    ID :<input type = "number" value="<%=person.getId()%>" name = "id" readonly/><br><br>
    NAME :<input type = "text" value="<%=person.getName()%>" name = "name"/><br><br>
    WAY :<input type = "text" value="<%=person.getWay()%>" name = "way"/><br><br>
    USD :<input type = "number" value="<%=person.getUSD()%>" min="0" max="2000000000" name = "usd"/><br><br>
    <input type="submit" value="Изменить"/>
</form>

<form action="DeletePerson" method="post">
    <input type="hidden" name="id" value="<%=person.getId()%>">
    <input type="submit" value="Удалить"/>
</form>

<form action="index.jsp">
    <button type="submit">На главную</button>
</form>

</body>
</html>
