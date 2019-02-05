<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.netcracker.edu.Person" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <title>Navigation</title>
</head>
<body>
    <h1 align="center">Список всех имеющихся Person</h1>
    <h2 align="center">Доступно изменение/удаление/сохранение Person'a по клику на ID</h2>
    <br><br>
    <div class="row">
        <div class="col-lg-6">
            <form action="/FindPersonServlet" method="post">
                <div class="input-group">
      <span class="input-group-btn">
        <button class="btn btn-default" type="submit">Найти!</button>
      </span>
                    <input type="text" class="form-control" name="criterion" placeholder="Поиск...">
                </div>
            </form>
        </div>
    </div>
    <br>

    <div class="btn-group btn-group-lg" role="group">
        <div class="btn-group" role="group">
            <a class="btn btn-primary btn-lg" role="button" href="/WaysToAddPersonServlet"> + Добавить Person'а</a>
        </div>
        <%--<div class="btn-group" role="group">--%>
        <%--<a class="btn btn-default btn-lg" role="button" href="/DownloadFile.jsp">Загрузить Person'а из файла</a>--%>
        <%--</div>--%>
    </div>

    <div class="panel panel-default">
        <div align="center" class="panel-heading">Список Person'ов</div>
        <table class="table">
            <thread>
                <tr bgcolor="#3486c6">
                    <th width="50px" height="20px">№</th>
                    <th width="70px" height="20px">Id</th>
                    <th width="150px" height="20px">Name</th>
                    <th width="150px" height="20px">Way</th>
                    <th width="150px" height="20px">USD</th>
                </tr>
            </thread>
            <% ArrayList<Person> list = (ArrayList<Person>) request.getAttribute("listOfPersons");
                int i =1;
                for(Person person : list) {%>
            <tr><td width="70px" height="20px"><%=i%></td>
                <td width="70px" height="20px" title="Тыкай, не боись">
                    <a href="/PersonPageServlet?id=<%=person.getId()%>&name=<%=person.getName()%>&way=<%=person.getWay()%>&usd=<%=person.getUSD()%>"><%=person.getId()%></a></td>
                <td width="150px" height="20px"><%=person.getName()%></td>
                <td width="150px" height="20px"><%=person.getWay()%></td>
                <td width="150px" height="20px"><%=person.getUSD()%></td></tr>
            <%i+=1;}%>
        </table>
    </div>
    <br>
    <div class="btn-group btn-group-lg" role="group">
        <div class="btn-group" role="group">
            <a class="btn btn-default btn-lg" onclick="history.back();">Вернуться</a>
        </div>
    </div>
</body>
</html>
