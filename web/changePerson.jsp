<%@ page import="program.PersonDB" %>
<%@ page import="program.Person" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Penguin
  Date: 2/1/2019
  Time: 12:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Изменить персону</title>
</head>
<body>
<h2 align="center">Изменение персоны</h2>
<hr align="center" width="500" size="2" color="#ff0000"/>
<style type="text/css">
    TABLE {
        width: 300px;
        border-bottom: none;
    }
    TH {
        text-align: left;
    }
    TD {
        text-align: left;
        border-bottom: 1px solid red;
    }
</style>
<table align="left">
    <caption><tr><th>ID</th><th>Name</th><th>Way</th><th>Usd</th></tr></caption>
    <%
        PersonDB personDB = PersonDB.getInstance();
        ArrayList<Person> people = personDB.selectALL();
        for(Person person : people){%>
    <tr>
        <td><%= person.getId()%></td>
        <td><%= person.getName()%></td>
        <td><%= person.getWay()%></td>
        <td><%= person.getUSD()%></td>
        <td></td>
        <td></td>
    </tr>
    <%}%>
</table>

<%--<div style="border-width: 0 0 0 1px;/*толщина*/ border-style: solid #FF0000;/*css do not work*/ padding-left: 170px;/*отступ слева*/ ">--%>
    <%--<hr style="height: 150px;/*высота*/ width: 1px;/*толщина*/">--%>
<div>Укажите новые данные персоны.</div>

<form action="ChangePerson" method="post">
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
