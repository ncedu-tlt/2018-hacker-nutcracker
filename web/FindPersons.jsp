<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.netcracker.edu.Person" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <title>Результат поиска</title>
</head>
<body>
    <h1 align="center">Результат поиска</h1>
    <h2 align="center">Доступно изменение/удаление/сохранение Person'a по клику на ID</h2>
    <br>
    <br>
    <% ArrayList<Person> list = (ArrayList<Person>) request.getAttribute("listOfPersons");
        int i =1;
        if (list.isEmpty()){%><h2 align="center">Совпадений не найдено</h2><%} else {%>

    <div class="panel panel-default">
        <div align="center" class="panel-heading">Список Person'ов</div>
        <table class="table table-striped">
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
            <%i+=1;}}%>
        </table>
    </div>
    <br>
    <br>
    <div class="btn-group btn-group-lg" role="group">
        <div class="btn-group" role="group">
            <a class="btn btn-default btn-lg" onclick="history.back();">Вернуться</a>
        </div>
    </div>
</body>
</html>