
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.netcracker.edu.entity.dao.CpeDao" %>
<%@ page import="com.netcracker.edu.entity.dao.PeDao" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html><html lang="ru"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%--<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">--%>
<%--<meta name="description" content="Пример на bootstrap 4: Ничего, кроме основ: скомпилированный CSS и JavaScript.">--%>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Главная страница</title>


    <style>
        body {
            /*background: #c7b39b url(images/map.JPG); !* Цвет фона и путь к файлу *!*/
            /*color: #fff; !* Цвет текста *!*/

            background: url(images/map.JPG) no-repeat center center fixed;
            /*color: white;*/
            background-size: cover;
        }
    </style>
</head>
<!-- CSS -->

<!-- Bootstrap core CSS -->
<link href="./welcomeBootstrapFiles/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-- /Bootstrap core CSS -->

<!-- OUR CSS -->
<link href="css/welcomeCSS.min.css">
<!-- /OUR CSS -->
<!-- /CSS -->

<style>

    .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
    }

    @media (min-width: 768px) {
        .bd-placeholder-img-lg {
            font-size: 3.5rem;
        }

    }
</style>

<link href="./welcomeBootstrapFiles/starter-template.css" rel="stylesheet">

<meta id="Reverso_extension___elForCheckedInstallExtension" name="Reverso extension" content="2.2.188"></head>

<body>
<script type="text/javascript" async="" src="./welcomeBootstrapFiles/watch.js.download"></script><script async="" src="./welcomeBootstrapFiles/analytics.js.download"></script><script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
        m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-4481610-59', 'auto');
    ga('send', 'pageview');
</script>
<!-- Yandex.Metrika counter -->
<script type="text/javascript"> (function (d, w, c) { (w[c] = w[c] || []).push(function() {
    try { w.yaCounter39705265 = new Ya.Metrika({ id:39705265, clickmap:true, trackLinks:true, accurateTrackBounce:true, webvisor:true }); }
    catch(e) { } }); var n = d.getElementsByTagName("script")[0], s = d.createElement("script"), f = function () { n.parentNode.insertBefore(s, n); };
    s.type = "text/javascript"; s.async = true; s.src = "https://mc.yandex.ru/metrika/watch.js"; if (w.opera == "[object Opera]")
    { d.addEventListener("DOMContentLoaded", f, false); } else { f(); } })(document, window, "yandex_metrika_callbacks");
</script> <noscript><div><img src="https://mc.yandex.ru/watch/39705265" style="position:absolute; left:-9999px;" alt="Yandex.Metrika" /></div></noscript>
<!-- /Yandex.Metrika counter -->

<!-- NAVIGATION BAR-->
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top" >
    <style>
<%--        это низ навигационного бара после которого идут остальные окна   50--%>
        body {
            padding-top: 70px;
        }
    </style>

    <a class="navbar-brand" href="">Название??</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" data-toggle="modal" data-target="#PeList">Список Pe</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="modal" data-target="#CpeList">Список Cpe</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="https://bootstrap-4.ru/docs/4.3.1/examples/starter-template/#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Добавить устройство</a>
                <div class="dropdown-menu" aria-labelledby="dropdown01">
                    <a class="dropdown-item" data-toggle="modal" data-target="#addCPE">Добавить Cpe</a>
                    <a class="dropdown-item" data-toggle="modal" data-target="#addPE">Добавить Pe</a>
                </div>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <button class="btn btn-secondary my-2 my-sm-0" type="submit">Поиск</button>
        </form>
    </div>
</nav>
<!-- /NAVIGATION BAR-->


<!-- Modal for List Cpe -->
<div class="modal fade" id="CpeList" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="CpeListTitle">Список CPE</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
                <!-- List Cpe -->
                <div class="list-group">
                    <c:forEach var="CpeDao" items="${CpeDaos}">
                    <a href="#" class="list-group-item list-group-item-action">${CpeDao.ip}</a>
                    </c:forEach>
                    <a href="#" class="list-group-item list-group-item-action">192.168.1.2</a>
                    <a href="#" class="list-group-item list-group-item-action">192.168.1.3</a>
                    <a href="#" class="list-group-item list-group-item-action">192.168.1.3</a>
                </div>
                <!-- /List Cpe -->
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<!-- /Modal for List Cpe -->


<!-- Modal for List Pe -->
<div class="modal fade" id="PeList" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="PeListTitle">Списко PE</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <!-- List Pe -->
            <div class="list-group">
                <c:forEach var="PeDao" items="${PeDaos}">
                    <a href="#" class="list-group-item list-group-item-action">${PeDao.ip}</a>
                </c:forEach>
                <a href="#" class="list-group-item list-group-item-action">192.168.1.2</a>
                <a href="#" class="list-group-item list-group-item-action">192.168.1.3</a>
                <a href="#" class="list-group-item list-group-item-action">192.168.1.3</a>
            </div>
            <!-- /List Pe -->
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<!-- /Modal for List Pe -->


<!-- Modal for add CPE-->
<div class="modal fade" id="addCPE" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="CPEModalLongTitle">Добавить CPE</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Form for data СPE-->
                <form>
                    <div class="form-group">
                        <label for="inputCPEIP">CPE IP address</label>
                        <input type="text" class="form-control" id="inputCPEIP" placeholder="192.168.1.1">
                    </div>
                    <div class="form-group">
                        <label for="inputPEIPAdressForCpe">PE IP address</label>
                        <input type="text" class="form-control" id="inputPEIPAdressForCpe" placeholder="192.168.1.1">
                    </div>

                    <div class="form-group row">
                        <div class="col-sm-10">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="InternetIsActive">
                                <label class="form-check-label" for="InternetIsActive">
                                    Включить интернет
                                </label>
                            </div>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary">Добавить</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
                </form>
                <!-- /Form for data СPE-->
            </div>
        </div>
    </div>
</div>
<!-- /Modal for add CPE-->

<!-- Modal for add PE-->
<div class="modal fade" id="addPE" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="PEModalLongTitle">Добавить PE</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <!-- Form for data PE-->
                <form>
                    <div class="form-group">
                        <label for="inputPEIP">PE IP address</label>
                        <input type="text" class="form-control" id="inputPEIP" placeholder="192.168.1.1">
                    </div>
                    <div class="form-group">
                        <label for="inputAddress1">Address 2</label>
                        <input type="text" class="form-control" id="inputAddress1" placeholder="Apartment, studio, or floor">
                    </div>
                    <button type="submit" class="btn btn-primary">Добавить</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
                </form>
                <!-- /Form for data PE-->
            </div>
        </div>
    </div>
</div>
<!-- /Modal for add PE-->

<!-- MAP -->
<main role="main" class="container">

    <div class="starter-template">
        <h1>тут будет карта типо</h1>
        <p class="lead">карту можно будет перемещать и ставить красивые иконочки и тд.<br> я очень надеюсь.</p>
    </div>
<%--    style="color: white"--%>
    <div class="pe" >PE</div>

    <div class="cpe">CPE</div>

</main>
<!-- /MAP -->

<!-- SCRIPTS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="./welcomeBootstrapFiles/jquery-3.3.1.slim.min.js.download" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<!--<script>window.jQuery || document.write('<script src="./welcomeBootstrapFiles/jquery-slim.min.js"><\/script>')</script><script src="./welcomeBootstrapFiles/bootstrap.bundle.min.js.download" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>-->
<script>window.jQuery || document.write('<script src="/docs/4.3.1/assets/js/vendor/jquery-slim.min.js"><\/script>')</script><script src="./welcomeBootstrapFiles/bootstrap.bundle.min.js.download" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
<!-- /SCRIPTS -->
</body>
</html>