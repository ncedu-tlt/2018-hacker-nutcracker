<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Сохранение Person</title>
</head>
<body>
    <h1 align="center">Выберите формат сохранения</h1>
    <% Integer id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    String way = request.getParameter("way");
    Integer usd = Integer.parseInt(request.getParameter("usd"));%>
    <form action="/SavePersonServlet" method="post">
        <input type="hidden" name="id" value="<%=id%>">
        <input type="hidden" name="name" value="<%=name%>">
        <input type="hidden" name="way" value="<%=way%>">
        <input type="hidden" name="usd" value="<%=usd%>">
        <input type="submit" name="format" value=".XML"/>
        <input type="submit" name="format" value=".CSV"/>
        <input type="submit" name="format" value=".JSON"/>
    </form>

    <form action="/Navigation" method="post">
        <input type="button" onclick="history.back();" value="Назад"/>
        <input type="submit" value="На главную"/>
    </form>
</body>
</html>
