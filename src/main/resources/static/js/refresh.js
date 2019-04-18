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