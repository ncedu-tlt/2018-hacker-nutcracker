<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Загрузка Person'a</title>
</head>
<body>
    <h1 align="center">Выберите файл для загрузки</h1>
    <h2 align="center">Ниче не работает! Не нажимать!</h2>
    <form action="/DownloadServlet" method="post">
        Выберите файлы: <input type="file" name="myFiles" multiple="true"><br><br>
        <input type="submit" value="Отправить"/>
    </form>
    <input type="button" onclick="history.back();" value="Назад"/>
</body>
</html>
