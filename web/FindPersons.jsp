<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.netcracker.edu.Person" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>Результат поиска</title>
</head>
<body>
    <h1 align="center">Результат поиска</h1>
    <h2 align="center">Доступно изменение/удаление/сохранение Person'a по клику на ID</h2>

    <%ArrayList<Person> list = (ArrayList<Person>) request.getAttribute("listOfPersons");
    int i =1;
    if (list.isEmpty()){%><h2 align="center">Совпадений не найдено</h2><%} else {%>
    <table border="1px" >
        <caption>Список Person'ов</caption>
        <thread>
            <tr bgcolor="#88d4eb">
                <th width="50px" height="20px" align="center">№</th>
                <th width="70px" height="20px" align="center">Id</th>
                <th width="150px" height="20px">Name</th>
                <th width="150px" height="20px">Way</th>
                <th width="150px" height="20px">USD</th>
            </tr>
        </thread>
        <% for(Person person : list) {
            %>
        <tr><td width="70px" height="20px" align="center"><%=i%></td>
            <td width="70px" height="20px" align="center">
                <a href="/PersonPageServlet?id=<%=person.getId()%>&name=<%=person.getName()%>&way=<%=person.getWay()%>&usd=<%=person.getUSD()%>"><%=person.getId()%></a></td>
            <td width="150px" height="20px"><%=person.getName()%></td>
            <td width="150px" height="20px"><%=person.getWay()%></td>
            <td width="150px" height="20px"><%=person.getUSD()%></td></tr>
        <%i+=1;}%>
    </table>
    <%}%>
    <input type="button" onclick="history.back();" value="Назад"/>
</body>
</html>
