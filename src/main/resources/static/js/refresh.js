function refresh1() {
    var XMLHttpRequestObject = false;
    if (window.XMLHttpRequest)
        XMLHttpRequestObject = new XMLHttpRequest();
    if (XMLHttpRequestObject) {
        XMLHttpRequestObject.open("GET", "http://localhost:8082/service/getListPe", true);
        XMLHttpRequestObject.onreadystatechange = function () {
            if (XMLHttpRequestObject.readyState == 4 && XMLHttpRequestObject.status == 200) {
                document.getElementById('peListId').innerHTML = XMLHttpRequestObject.responseText;
            }
        }
        XMLHttpRequestObject.send(null);
    }
}

function refresh2() {
    var XMLHttpRequestObject = false;
    if (window.XMLHttpRequest)
        XMLHttpRequestObject = new XMLHttpRequest();
    if (XMLHttpRequestObject) {
        XMLHttpRequestObject.open("GET", "http://localhost:8082/service/getListCpe", true);
        XMLHttpRequestObject.onreadystatechange = function () {
            if (XMLHttpRequestObject.readyState == 4 && XMLHttpRequestObject.status == 200) {
                document.getElementById('cpeListId').innerHTML = XMLHttpRequestObject.responseText;
            }
        }
        XMLHttpRequestObject.send(null);
    }
}

function refresh3() {
    var XMLHttpRequestObject = false;
    if (window.XMLHttpRequest)
        XMLHttpRequestObject = new XMLHttpRequest();
    if (XMLHttpRequestObject) {
        XMLHttpRequestObject.open("GET", "http://localhost:8082/service/refreshPeAndCpe", true);
        XMLHttpRequestObject.onreadystatechange = function () {
            if (XMLHttpRequestObject.readyState == 4 && XMLHttpRequestObject.status == 200) {
                document.getElementById('refreshPeAndCpe').innerHTML = XMLHttpRequestObject.responseText;
            }
        }
        XMLHttpRequestObject.send(null);
    }
}

function refreshXY(ip, x, y) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8082/service/refreshXY/" + ip + "/" + x + "/" + y, false);
    xhr.send(null);
}

function sendCpe(link) {
    var val1 = document.getElementById('inputCpeIp').value;
    var val2 = document.getElementById('inputPeIpAddressForCpe').value;
    var box1 = document.getElementById('InternetActive');
    var bool;
    var xhr = new XMLHttpRequest();

    if (box1.checked) {
        bool = 'true';
    } else {
        bool = 'false';
    }
    var json = JSON.stringify({
        ip: val1,
        peIpAddress: val2,
        isInternetActive: bool
    });
    var json2 = JSON.stringify({
        ip: val1,
        peIpAddress: val2,
        internetActive: bool
    });
    var xhr2 = new XMLHttpRequest();
    xhr2.open("GET", "http://localhost:8082/service/addCpe/" + json2, true);
    xhr2.send(null);
    xhr.open("GET", "" + link + '/' + json, true);
    xhr.send(null);
}

function sendPe(link) {
    var val1 = document.getElementById('inputPeIp').value;
    var xhr = new XMLHttpRequest();

    var json = JSON.stringify({
        ip: val1,
    });

    var json2 = JSON.stringify({
        ip: val1,
    });

    var xhr2 = new XMLHttpRequest();
    xhr2.open("GET", "http://localhost:8082/service/addPe/" + json2, true);
    xhr2.send(null);

    xhr.open("GET", "" + link + '/' + json, true);
    xhr.send(null);
}

function deleteCpe(link) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "" + link, true);
    xhr.send(null);
    var xhr2 = new XMLHttpRequest();
    var cpe = link.substring(33);
    xhr2.open("GET", "http://localhost:8082/service/deleteCpe/" + cpe, true);
    xhr2.send(null);
}

function deletePe(link) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "" + link, true);
    xhr.send(null);
    var xhr2 = new XMLHttpRequest();
    var pe = link.substring(32);
    xhr2.open("GET", "http://localhost:8082/service/deletePe/" + pe, true);
    xhr2.send(null);
}

function changeCpeInternet(link) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "" + link, true);
    xhr.send(null);
}

function changePeFan(link) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "" + link, true);
    xhr.send(null);
}

function lines() {
    var XMLHttpRequestObject = false;
    if (window.XMLHttpRequest)
        XMLHttpRequestObject = new XMLHttpRequest();
    if (XMLHttpRequestObject) {
        XMLHttpRequestObject.open("GET", "http://localhost:8082/service/lines", true);
        XMLHttpRequestObject.onreadystatechange = function () {
            if (XMLHttpRequestObject.readyState == 4 && XMLHttpRequestObject.status == 200) {
                document.getElementById('svgId').innerHTML = XMLHttpRequestObject.responseText;
            }
        }
        XMLHttpRequestObject.send(null);
    }
}