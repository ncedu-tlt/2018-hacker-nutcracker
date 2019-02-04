<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.netcracker.edu.Person" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>Страница действия над Person</title>
</head>
<body>
<% Person person = (Person) request.getAttribute("Person");%>
<%ArrayList<String> ways = (ArrayList<String>) request.getAttribute("ways");%>
    <h1 align="center"> Данные Person'a </h1>

    <form action="/UpdateServlet" method="post">
        ID :<input type = "number" value="<%=person.getId()%>" name = "id" readonly/><br><br>
        NAME :<input type = "text" value="<%=person.getName()%>" name = "name"/><br><br>
        WAY :<input type = "text" value="<%=person.getWay()%>" name = "way"/>
        <select name="selectWay">
            <option value ="default">Выберите way из предложенных или создайте новый</option>
            <%for (String way: ways){%>
            <option value="<%=way%>"><%=way%></option><%}%>
        </select><br><br>
        USD :<input type = "number" value="<%=person.getUSD()%>" min="0" max="2000000000" name = "usd"/><br><br>
        <input type="submit" value="Изменить"/>
    </form>

    <form action="/DeleteServlet" method="post">
        <input type="hidden" name="id" value="<%=person.getId()%>">
        <input type="submit" value="Удалить"/>
    </form>

    <form action="/SavePersonInFormat" method="post">
        <input type="hidden" name="id" value="<%=person.getId()%>">
        <input type="hidden" name="name" value="<%=person.getName()%>">
        <input type="hidden" name="way" value="<%=person.getWay()%>">
        <input type="hidden" name="usd" value="<%=person.getUSD()%>">
        <input type="submit" value="Сохранить в файл"/>
    </form>

    <input type="button" onclick="history.back();" value="Назад"/>
</body>
</html>
