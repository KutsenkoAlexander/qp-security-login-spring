/**
 * Created by SamsungR60P on 29.06.2015.
 */
var elem;
var user;
function getJSON(loginOrElement, putUrl, idElement) {
    var xmlhttp = new XMLHttpRequest();
    var url = putUrl;
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            if (loginOrElement) {
                readJsonForLogout(xmlhttp.responseText, idElement);
            }
            readJson(xmlhttp.responseText, idElement);
        }
    }
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function readJson(response, idElem) {
    var arr = JSON.parse(response);
    var out = arr.fio;
    document.getElementById(idElem).innerHTML = out;
}

function readJsonForLogout(response, idElem) {
    var arr = JSON.parse(response);
    var out = arr.fio;
    document.getElementById(idElem).innerHTML = out + ' - Выйти';
}

function getFioFromJson(urlPath, idElem){
    $("#add").click(function() {
        $.ajax({
            url: urlPath,
            type: "GET",
            dataType: "text",
            success: function(data) {
                var jsonparse = $.parseJSON(data);
                elem = idElem;
                user = jsonparse.fio;
                $('.'+elem).val(user);
            }
        });
    });
}

function getFioFromJsonControler (urlPath, idElem) {
    $.ajax({
        url: urlPath,
        type: "GET",
        dataType: "text",
        success: function (dataControler) {
            var jsonparse = $.parseJSON(dataControler);
            elem = idElem;
            user = jsonparse.fio;
        }
    });
}

function getFioFromJsonForLogin(urlPath, idElem) {
    $(document).ready(function () {
        var UserId;
        $.ajax({
            url: urlPath,
            type: "GET",
            dataType: "text",
            success: function (data) {
                var jsonparse = $.parseJSON(data);
                var logoutData = ' - Выйти';
                var parseData = jsonparse.fio + logoutData;
                getUserApps(jsonparse.userId);
                //UserId = jsonparse.UserId;
                $('.'+idElem).html(parseData);
            }
        });
    });
}

//*********************************************************************************
//Функция, которая будет получать список доступных приложения пользователю
//*********************************************************************************
function getUserApps(id) {
    $.ajax({
        type: 'GET',
        url: 'http://192.168.31.200:8888/Applistuser/userapps/'+id,
        callbackParameter: 'callback',
        dataType: 'jsonp',
        timeout: 10000,
        success: function (json) {
            var appData = '';
            $.each(json, function (index, app){
                appData = appData+'<a href="'+app.appLink+'">'+app.appName+'</a>';
            });
            $('.list_app').html(appData);
        },
        error: function () {
            alert('Список приложений не определен!')
        }
    });
}
