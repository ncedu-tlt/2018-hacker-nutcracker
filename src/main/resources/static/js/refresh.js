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

function message() {
    var XMLHttpRequestObject = false;
    if (window.XMLHttpRequest)
        XMLHttpRequestObject = new XMLHttpRequest();
    if (XMLHttpRequestObject) {
        XMLHttpRequestObject.open("GET", "http://localhost:8082/service/message", true);
        XMLHttpRequestObject.onreadystatechange = function () {
            if (XMLHttpRequestObject.readyState == 4 && XMLHttpRequestObject.status == 200) {
                document.getElementById('messageId').innerHTML = XMLHttpRequestObject.responseText;
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
    alert("123" + json);
    xhr.open("GET", "" + link + '/' + json, true);
    xhr.send(null);
}

function sendPe(link) {
    var val1 = document.getElementById('inputPeIp').value;
    var xhr = new XMLHttpRequest();

    var json = JSON.stringify({
        ip: val1,
    });

    xhr.open("GET", "" + link + '/' + json, true);
    xhr.send(null);
}

function deleteCpe(link) {
    var val1 = document.getElementById('deleteCpeIp').value;
    xhr.open("GET", "" + link + '/' + val1, true);
    xhr.send(null);
}

function deletePe(link) {
    var val1 = document.getElementById('deletePeIp').value;
    xhr.open("GET", "" + link + '/' + val1, true);
    xhr.send(null);
}

function changeCpeInternet(link) {
    var val1 = document.getElementById('СpeIp').value;
    xhr.open("GET", "" + link + '/' + val1, true);
    xhr.send(null);
}

function changePeFan(link) {
    var val1 = document.getElementById('PeIp').value;
    xhr.open("GET", "" + link + '/' + val1, true);
    xhr.send(null);
}
function message() {
    var XMLHttpRequestObject = false;
    if (window.XMLHttpRequest)
        XMLHttpRequestObject = new XMLHttpRequest();
    if (XMLHttpRequestObject) {
        XMLHttpRequestObject.open("GET", "http://localhost:8082/service/message", false);
        XMLHttpRequestObject.onreadystatechange = function () {
            if (XMLHttpRequestObject.readyState == 4 && XMLHttpRequestObject.status == 200) {
                document.getElementById('messageId').innerHTML = XMLHttpRequestObject.responseText;
            }
        }
        XMLHttpRequestObject.send(null);
    }
}