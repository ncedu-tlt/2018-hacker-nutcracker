<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <title>Ошибка в действиях!</title>
</head>
<body>
    <%String exception = request.getParameter("exception");
    if(exception=="id"){%>
    <h1 align="center">Введенный ID уже существует! Повторите действия с другим ID!</h1>
    <%} else{%>
    <h1 align="center">Выберите Way из списка или создайте новый!</h1>
    <%}%>
    <div class="btn-group btn-group-lg" role="group">
        <div class="btn-group" role="group">
            <a class="btn btn-default btn-lg" onclick="history.back();">Вернуться</a>
        </div>
    </div>
</body>
</html>
