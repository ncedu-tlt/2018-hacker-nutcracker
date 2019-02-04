<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <nav class="navbar navbar-expand-md navbar-dark bg-dark sticky-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <h1 class="navbar-brand">Меню действий</h1>
            </div>

            <div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Главная страница</a></li>
                    <li><a href="/FormToAddPersonServlet">Добавить нового Person'a</a></li>
                    <li><a href="/ViewAllServlet">Изменить Person'a</a></li>
                    <li><a href="/ViewAllServlet">Удалить Person'a</a></li>
                    <li><a href="/ViewAllServlet">Показать всех Person</a></li>
                    <li><a href="/ViewAllServlet">Сохранить Person'а в файл</a></li>
                    <li><a href="/Download">Загрузить Person'а из XML файла</a></li>
                </ul>
            </div>
        </div>
    </nav>
</body>
</html>
