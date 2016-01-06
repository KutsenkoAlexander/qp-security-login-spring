/**
 * Created by Aleksandr on 16.08.15.
 */
var idProduct = 0; //id produc
var nameProduct = null;//name product
var rezultIncomeProduct = false;//check result income product
var fromPeriod = 0;//from period
var toPeriod = getCurrentDate();//to period

wasCliked = false;

function buttonParamsSearch(){
    $('#btn_params').click(function () {
        $( "#params" ).toggle();
    });
    $('#wrapper').click(function () {
        document.getElementById('params').style.display = "none";
    });
}

//DATA GET PRODUCT START
function periodStart() {
    $(function () {
        $.datepicker.setDefaults(
            $.extend($.datepicker.regional['ru'])
        );
        $("#period_start").datepicker();
    });
}

//DATA GET PRODUCT END
function periodEnd() {
    $(function () {
        $.datepicker.setDefaults(
            $.extend($.datepicker.regional['ru'])
        );
        $("#period_end").datepicker();
    });
}

function serchInput() {
    $('#inpt_search').autocomplete({
        source: function (request, response) {
            $.ajax({
                url: '/QP/api/search/like_search',
                dataType: 'json',
                data: {
                    name: request.term // поисковая фраза
                },
                success: function (data) {
                    response($.map(data, function (item) {
                        return {
                            id: item.likeId,
                            value: item.likeName
                        }
                    }));
                }
            });
        },
        select: function (event, ui) {
            if (ui.item.value == null) {
                $("#inpt_search").val("Продукция не найдена");
                $("#hvr_inpt_search").val(null);
            } else {
                $("#inpt_search").val(ui.item.value);
                $("#hvr_inpt_search").val(ui.item.id);
            }
            return false;
        },
        minLength: 2 // начинать поиск
    });
}

function checkRezControl(){
    $("#param_control").on("click", function () {
        $(this).prop('checked', $(this).prop('checked'));
        alert("true");
    });
}

function getValueInputSearch(){
    if($("#hvr_inpt_search").val() != '') idProduct = $("#hvr_inpt_search").val();
    if($("#inpt_search").val() != null) nameProduct = $("#inpt_search").val();
    if($("#param_control").prop('checked')) rezultIncomeProduct = true;
    if($("#period_start").val() != null) fromPeriod = $("#period_start").val();
    if($("#period_end").val() != null) toPeriod = $("#period_end").val();
}

function checkRezControl(){
    $(".no_term").on("click", function () {
        $(this).attr('checked', $(this).attr('checked'));
    });
}

function searchFunction(){
    if(wasCliked != true) {
        doSearch();
    } else {
        searchPagination(pageNumber);
    }
}

function doSearch(){
    $("#btn_search").click(function(){
        search = true;
        wasCliked = true;
        pageNumber = 1;
        $("#inpt_search").val('');
        searchPagination(pageNumber);
    });
}

function searchPagination(page, mgId){
    search = true;
    $("#page_number").html(page);
    getValueInputSearch();
    var param_search = {
        page: page,
        magazine: mgId,
        idProduct: idProduct,
        nameProduct: nameProduct,
        rezultIncomeProduct: rezultIncomeProduct,
        fromPeriod: fromPeriod,
        toPeriod: toPeriod
    };
    $.ajax({
        url: '/QP/api/search/do_search',
        type: 'GET',
        data: param_search,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function (data) {
            document.getElementById('loading_wrapper').style.display = "none";
            var prData = $("#title_page").html("Результат поиска");
            $.each(data, function (index, prName) {
                prData = prData + '<tr>';
                if (prName !== null) {
                    prData = prData + '<td>' + prName.Name + '</td>' +
                        '<td>' + prName.Lot + '</td>' +
                        '<td style="text-align: center">' + prName.DateLastModify + '</td>' +
                        '<td style="text-align: center" class="td_control">' + prName.ControlResult + '</td>' +
                        '<td class="more">' +
                        '<a href="#open" class="td">Подробнее</a>' +
                        '<input type="hidden" value="' + prName.ProductId + '"/></td>';
                } else {
                    prData = prData + '<td>&nbsp;<td>';
                }
                prData = prData + '</tr>';

            });
            $('#table_content').html(prData);
            $('.td_control:contains("Новая")').parent().addClass("new_product");
            $('.td_control:contains("Не пройден")').parent().addClass("control_this");
            //get id from hidden input after a
            $('.td').click(function () {
                var idProd = $(this).next().val();
                getProduct(idProd);
            });
            idProduct = 0; //id produc
            nameProduct = null;//name product
            rezultIncomeProduct = false;//check result income product
            fromPeriod = 0;//from period
            toPeriod = getCurrentDate();//to period
        },
        error: function (result) {
            idProduct = 0; //id produc
            nameProduct = null;//name product
            rezultIncomeProduct = false;//check result income product
            fromPeriod = 0;//from period
            toPeriod = getCurrentDate();//to period
            alert("Ошибка обработки поискового запроса!");
        }
    });
}

//FOR TEST IN FUTURE
//function getFioFromJsonControler (urlPath, idElem) {
//    $.ajax({
//        url: urlPath,
//        type: "GET",
//        dataType: "text",
//        success: function (dataControler) {
//            var jsonparse = $.parseJSON(dataControler);
//            elem = idElem;
//            user = jsonparse.fio;
//        }
//    });
//}