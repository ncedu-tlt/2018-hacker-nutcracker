<%@ page import="com.netcracker.edu.database.DBQueries" %>
<%@ page import="com.netcracker.edu.database.OracleDriverManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <title>Welcome Page</title>
</head>
<body>
<%OracleDriverManager oracle = new OracleDriverManager();
    DBQueries.getInstance(oracle.openOracleConnection());%>
<div class="jumbotron">
    <div class="container">
        <h1>Достаточно ли френдли?</h1>
        <p>Считаю что достаточно, поехали =)</p>
        <p><a class="btn btn-primary btn-lg" href="/ViewAllServlet" role="button">Начать работу</a></p>
    </div>
</div>
</body>
</html>
