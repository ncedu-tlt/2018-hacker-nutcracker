<%@ page import="program.PersonDB" %>
<%@ page import="program.Person" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Penguin
  Date: 2/1/2019
  Time: 12:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Удалить персону</title>
</head>
<body>
<h2 align="center">Удалить персону</h2>
<hr align="center" width="500" size="2" color="#ff0000"/>
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
<table>
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

<div>Ведите id персоны которую хотите удалить.</div>
<form action="DeletePerson" method="post">
    ID: <input name="id"/>
    <br><br>
    <input type="submit" value="Подтвердить">
</form>


<form action="index.jsp">
    <button type="submit">На главную</button>
</form>
</body>
</html>
