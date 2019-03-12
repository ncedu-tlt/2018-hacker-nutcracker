<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <title>Загрузка</title>
</head>
<body>
    <h1 align="center">Выберите файл для загрузки</h1>
<br>
<br>
<br>
<br>
    <div class="container">
    <form action="/artifact/DownloadFileServlet" method="post" enctype="multipart/form-data">
        <div class="form-group row">
            <label for="file" class="col-sm-2 col-form-label">Выберите файл</label>
            <div class="col-sm-10">
                <input type="file" class="form-control-file" id="file" name="file">
            </div>
        </div>
        <input type="submit" class="btn btn-primary" name="Отправить" >
    </form>
    </div>
<br>
    <div class="btn-group btn-group-lg" role="group">
        <div class="btn-group" role="group">
            <a class="btn btn-default btn-lg" onclick="history.back();">Вернуться</a>
        </div>
    </div>
</body>
</html>