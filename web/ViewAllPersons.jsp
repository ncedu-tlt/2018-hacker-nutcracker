<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.netcracker.edu.Person" %>
<html>
<head>
    <title>Все записи в базе данных</title>
</head>
<body>
    <h1 align="center">Список всех имеющихся Person</h1>
    <h2 align="center">Доступно изменение и удаление Person'a по клику на ID</h2>
    <%ArrayList<Person> list = (ArrayList<Person>) request.getAttribute("listOfPersons");
    int i =1;%>
        <table border="1px" >
            <caption>Список Person'ов</caption>
            <colgroup>
                <col style="background-color:#3addeb">
                <col span="4" style="background-color:#fffae1">
            </colgroup>
            <thread>
                <tr bgcolor="skyblue">
                    <th width="50px" height="20px" align="center">№</th>
                    <th width="70px" height="20px" align="center">Id</th>
                    <th width="150px" height="20px">Name</th>
                    <th width="150px" height="20px">Way</th>
                    <th width="150px" height="20px">USD</th>
                </tr>
            </thread>
    <%for(Person person : list) {%>
         <tr><td width="70px" height="20px" align="center"><%=i%></td>
             <td width="70px" height="20px" align="center" title="Тыкай, не боись">
                 <a href="/PersonPageServlet?id=<%=person.getId()%>&name=<%=person.getName()%>&way=<%=person.getWay()%>&usd=<%=person.getUSD()%>"><%=person.getId()%></a></td>
             <td width="150px" height="20px"><%=person.getName()%></td>
             <td width="150px" height="20px"><%=person.getWay()%></td>
             <td width="150px" height="20px"><%=person.getUSD()%></td></tr>
    <%i+=1;}%>
        </table>
    <input type="button" onclick="history.back();" value="Назад"/>
</body>
</html>
