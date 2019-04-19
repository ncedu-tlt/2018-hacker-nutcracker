function refresh(){
    var XMLHttpRequestObject = false;
    if (window.XMLHttpRequest)
        XMLHttpRequestObject = new XMLHttpRequest();
    if (XMLHttpRequestObject)
    {
        XMLHttpRequestObject.open("GET","http://localhost:8082/service/getAllLists",true);
        XMLHttpRequestObject.onreadystatechange = function(){
            if (XMLHttpRequestObject.readyState == 4 && XMLHttpRequestObject.status == 200){
                document.getElementById('result').innerHTML = XMLHttpRequestObject.responseText;
            }
        }
        XMLHttpRequestObject.send(null);
    }
}

function sendCpe(link) {
    var val1 = document.getElementById('inputCpeIp').value;
    var val2 = document.getElementById('inputPeIpAdressForCpe').value;
    var chbox = document.getElementById('InternetActive');
    var boolean;
    var xhr = new XMLHttpRequest();

    if (chbox.checked) {
        boolean = 'true';
    }
    else {
        boolean = 'false';
    }
    var json = JSON.stringify({
        ip: val1,
        peIpAddress: val2,
        internetActive: boolean
    });

    alert(json);
    xhr.open("GET", ""+link+'/'+json, true);
    xhr.send(null);
}

function sendPe(link) {
    var val1 = document.getElementById('inputPeIp').value;
    var xhr = new XMLHttpRequest();

    var json = JSON.stringify({
        ip: val1,
    });

    alert(json);
    xhr.open("GET", ""+link+'/'+json, true);
    xhr.send(null);
}

function deleteCpe(link) {
    var val1 = document.getElementById('deleteCpeIp').value;
    xhr.open("GET", ""+link+'/'+val1, true);
    xhr.send(null);
}

function deletePe(link) {
    var val1 = document.getElementById('deletePeIp').value;
    xhr.open("GET", ""+link+'/'+val1, true);
    xhr.send(null);
}

function changeCpeInternet(link) {
    var val1 = document.getElementById('СpeIp').value;
    xhr.open("GET", ""+link+'/'+val1, true);
    xhr.send(null);
}

function changePeFan(link) {
    var val1 = document.getElementById('PeIp').value;
    xhr.open("GET", ""+link+'/'+val1, true);
    xhr.send(null);
}