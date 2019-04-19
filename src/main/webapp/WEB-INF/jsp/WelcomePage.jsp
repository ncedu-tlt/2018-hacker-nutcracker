<!DOCTYPE html>
<html lang="ru">

    <%@ page import="com.netcracker.edu.entity.dao.CpeDao" %>
    <%@ page import="com.netcracker.edu.entity.dao.PeDao" %>
    <%@ page import="java.util.HashMap" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
    <% HashMap<Integer,String> linksCpe =(HashMap<Integer, String>) request.getAttribute("linksCpe");%>
    <% HashMap<Integer,String> linksPe =(HashMap<Integer, String>) request.getAttribute("linksPe");%>
<head>
    <meta http-equiv="content-Type" content="text/html" charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <title>Главная страница</title>
    <style>
        body{
            background: #c7b39b url(../images/map1.jpg); /* Цвет фона и путь к файлу */
            /*color: #fff; !* Цвет текста *!*/
            /*background-image: url(images/map.jpg);*/
            backdrop-repeat: no-repeat;
            color: black;
            background-size: cover;
        }
    </style>
    <!-- CSS -->
    <!-- OUR CSS -->
    <link href="../css/welcomeCSS.min.css" rel="stylesheet" type="text/css"/>
    <!-- /OUR CSS -->

    <!-- DRAGIN CSS -->
    <style>
        #container {
            width: 1920px;
            height: 1030px;
            position: relative;
            display: flex;
            align-items: center;
            justify-content: center;
            overflow: hidden;
            border-radius: 7px;
            touch-action: none;
        }

        .item {
            border-radius: 50%;
            touch-action: none;
            user-select: none;
        }

        .two {
            width: 60px;
            height: 60px;
            background-color: rgba(196, 241, 190, 1);
            border: 10px solid rgba(136, 136, 136, .5);
        }

        .three {
            width: 40px;
            height: 40px;
            background-color: rgb(0, 255, 231);
            border: 10px solid rgba(136, 136, 136, .5);
        }

        .item:active {
            opacity: .75;
        }

        .item:hover {
            cursor: pointer;
        }

        h1 {
            margin-bottom: 10px;
        }

    </style>
    <!-- /DRAGIN CSS -->
        <style>
        .descr{
            display:none;
            padding-top: 13px;
            padding-left: 10px;
            margin-top:-25px;
            background:#f3f3f3;
            -moz-box-shadow:0 5px 5px rgba(0,0,0,0.3);
            -webkit-box-shadow:0 5px 5px rgba(0,0,0,0.3);
            box-shadow:0 5px 5px rgba(0,0,0,0.3);
        }
        .two:hover .descr{
            display:block;
            margin-left:55px;
            height:200%;
            top:100px;
            z-index:9999;
            width:200px;
        }
        .three:hover .descr{
            display:block;
            /*position:absolute;*/
            margin-left:35px;
            height:300%;
            top:100px;
            z-index:9999;
            width:200px;
        }
    </style>
    <!-- /CSS -->
    <link href="../welcomeBootstrapFiles/starter-template.css" rel="stylesheet">
    <meta id="Reverso_extension___elForCheckedInstallExtension" name="Reverso extension" content="2.2.188"></head>
</head>
<body>
<script src="/js/refresh.js"></script><!-- Script for generate PE/CPE-->
    <style>
        body{
            width: 1920px;
            height: 1030px;
        }
    </style>

    <script type="text/javascript" async="" src="../welcomeBootstrapFiles/watch.js.download"></script><script async="" src="../welcomeBootstrapFiles/analytics.js.download"></script><script>
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
    </script>
    <noscript>
        <div>
            <img src="https://mc.yandex.ru/watch/39705265" style="position:absolute; left:-9999px;" alt="Yandex.Metrika" />
        </div>
    </noscript>
    <!-- /Yandex.Metrika counter -->

    <!-- NAVIGATION BAR-->
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top" >
    <style>
<%--        это низ навигационного бара после которого идут остальные окна   50--%>
        body {
            padding-top: 50px;
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
<%--                    <c:forEach var="CpeDao" items="${CpeDaoList}">--%>
<%--                    <a href="#" class="list-group-item list-group-item-action">${CpeDao.ip}</a>--%>
<%--                    </c:forEach>--%>
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
            <div id="result">
            <div class="list-group">
            </div>
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
                <form name="addCpeForSend">
                    <div class="form-group">
                        <label for="inputCpeIp">CPE IP address</label>
                        <input type="text" class="form-control" id="inputCpeIp" name="inputCpeIp" placeholder="192.168.1.1">
                    </div>
                    <div class="form-group">
                        <label for="inputPeIpAdressForCpe">PE IP address</label>
                        <input type="text" class="form-control" id="inputPeIpAdressForCpe" placeholder="192.168.1.1">
                    </div>

                    <div class="form-group row">
                        <div class="col-sm-10">
                            <div class="form-check">
                                <label class="form-check-label" for="InternetActive">
                                    <input class="form-check-input" type="checkbox" id="InternetActive">
                                    Включить интернет
                                </label>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="sendCpe('<%=linksCpe.get(1)%>')">Добавить</button>
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
                        <label for="inputPeIp">PE IP address</label>
                        <input type="text" class="form-control" id="inputPeIp" placeholder="192.168.1.1">
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="sendPe('<%=linksPe.get(1)%>')">Добавить</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
                </form>
                <!-- /Form for data PE-->
            </div>
        </div>
    </div>
</div>
    <!-- /Modal for add PE-->

<!-- MAP -->
    <div id="container">
        <c:forEach  items="${PeDaoList}" var="PeDao">
            <div class="item two newPos">
                <style>
                    .newPos{
                        position: absolute; /* Абсолютное позиционирование */
                        bottom: ${PeDao.coordinateX}px; /* Положение от нижнего края */
                        right: ${PeDao.coordinateY}px; /* Положение от правого края */
                        line-height: 1px;
                    }
                </style>

                PE
                <div class="descr">
                    <p>${PeDao.ip}</p>
                    <p >${PeDao.downlinkSpeed}</p>
                    <p >${PeDao.temperature} </p>
                    <p >${PeDao.fanActive} </p>
                </div>
            </div>
        </c:forEach>
        <c:forEach  items="${CpeDaoList}" var="CpeDao">
            <div class="item three newPos1">
                <style>
                    .newPos1{
                        position: absolute; /* Абсолютное позиционирование */
                        bottom: ${CpeDao.coordinateX}px; /* Положение от нижнего края */
                        right: ${CpeDao.coordinateY}px; /* Положение от правого края */
                        line-height: 1px;
                    }
                </style>
                CPE
                <div class="descr">
                    <p>${CpeDao.ip}</p>
                    <p >${CpeDao.downlinkSpeed}</p>
                    <p >${CpeDao.internetActive} </p>
                </div>
            </div>
        </c:forEach>

        <div class="item three newPos2">
            <style>
                .newPos2{
                    position: absolute; /* Абсолютное позиционирование */
                    bottom: 300px; /* Положение от нижнего края */
                    right: 300px; /* Положение от правого края */
                    line-height: 1px;
                }
            </style>
            CPE
            <div class="descr">
                <p>ПОШЁЛ НАХУЙ</p>
                <p >ПОШЁЛ НАХУЙ</p>
                <p >ПОШЁЛ </p>
            </div>

        </div>
    </div>
<!-- /MAP -->

<!-- Test Artem Scrypt-->
<script >setInterval('refresh()', 1000)</script><!-- call refresh.js-->
<!-- result rafresh.js-->
<!-- OUR SCRIPTS -->
<!-- SCRIP FOR DRAGING -->
<script>
    var container = document.querySelector("#container");
    var activeItem = null;

    var active = false;

    container.addEventListener("touchstart", dragStart, false);
    container.addEventListener("touchend", dragEnd, false);
    container.addEventListener("touchmove", drag, false);

    container.addEventListener("mousedown", dragStart, false);
    container.addEventListener("mouseup", dragEnd, false);
    container.addEventListener("mousemove", drag, false);

    function dragStart(e) {

        if (e.target !== e.currentTarget) {
            active = true;

            // this is the item we are interacting with
            activeItem = e.target;

            if (activeItem !== null) {
                if (!activeItem.xOffset) {
                    activeItem.xOffset = 0;
                }

                if (!activeItem.yOffset) {
                    activeItem.yOffset = 0;
                }

                if (e.type === "touchstart") {
                    activeItem.initialX = e.touches[0].clientX - activeItem.xOffset;
                    activeItem.initialY = e.touches[0].clientY - activeItem.yOffset;
                } else {
                    console.log("doing something!");
                    activeItem.initialX = e.clientX - activeItem.xOffset;
                    activeItem.initialY = e.clientY - activeItem.yOffset;
                }
            }
        }
    }

    function dragEnd(e) {
        if (activeItem !== null) {
            activeItem.initialX = activeItem.currentX;
            activeItem.initialY = activeItem.currentY;
        }

        active = false;
        activeItem = null;
    }

    function drag(e) {
        if (active) {
            if (e.type === "touchmove") {
                e.preventDefault();

                activeItem.currentX = e.touches[0].clientX - activeItem.initialX;
                activeItem.currentY = e.touches[0].clientY - activeItem.initialY;
            } else {
                activeItem.currentX = e.clientX - activeItem.initialX;
                activeItem.currentY = e.clientY - activeItem.initialY;
            }

            activeItem.xOffset = activeItem.currentX;
            activeItem.yOffset = activeItem.currentY;

            setTranslate(activeItem.currentX, activeItem.currentY, activeItem);
        }
    }

    function setTranslate(xPos, yPos, el) {
        el.style.transform = "translate3d(" + xPos + "px, " + yPos + "px, 0)";
    }
</script>
    <!-- /SCRIP FOR DRAGING -->
    <!-- /OUR SCRIPTS -->
    <!-- SCRIPTS -->

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <%--<script src="../welcomeBootstrapFiles/jquery-3.3.1.slim.min.js.download" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
    <!--<script>window.jQuery || document.write('<script src="./welcomeBootstrapFiles/jquery-slim.min.js"><\/script>')</script><script src="./welcomeBootstrapFiles/bootstrap.bundle.min.js.download" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>-->
    <%--<script>window.jQuery || document.write('<script src="/docs/4.3.1/assets/js/vendor/jquery-slim.min.js"><\/script>')</script><script src="../welcomeBootstrapFiles/bootstrap.bundle.min.js.download" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>--%>
    <!-- /SCRIPTS -->
</body>
</html>