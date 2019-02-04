<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.netcracker.edu.Person" %>
<html>
<head>
    <title>Все записи в базе данных</title>
</head>
<body>
    <h1 align="center">Список всех имеющихся Person</h1>
    <h2 align="center">Доступно изменение/удаление/сохранение Person'a по клику на ID</h2>
    <%ArrayList<Person> list = (ArrayList<Person>) request.getAttribute("listOfPersons");
    int i =1;%>
    <form action="/FindPersonServlet" method="post">
        <input required type="radio" name="field" value="id"> ID
        <input required type="radio" name="field" value="name"> Name
        <input required type="radio" name="field" value="way"> Way
        <input required type="radio" name="field" value="usd"> USD
        <input required type="text" name="criterion" value="Поиск по критериям"
               onfocus="if (this.value == 'Поиск по критериям') {this.value = '';}"
               onblur="if (this.value == '') {this.value = 'Поиск по критериям';}"><br>
        <input type="submit" value="Поиск">
    </form>
    <h3>Список Person'ов</h3>
        <table border="1px" >
            <thread>
                <tr bgcolor="#88d4eb">
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
    <input align="center" type="button" onclick="history.back();" value="Назад"/>
</body>
</html>
