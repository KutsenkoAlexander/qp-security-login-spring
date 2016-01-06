/**
 * Created by kycenko on 18.06.15.
 */
var pageNumber = 1;
var magazine;
var path;

var attachDocs = [];
var attachFiles = [];

var search = false;

var result_control = "999";

//NAME MAGAZINE FOR SHOW ROLE USER
var mmm = '';
var qqq = '';
var yyy = '';
var eee = '';
var rrr = '';
var ttt = '';

var role = null;

//All functions upload
function uploadAll(){
    uploadFiles(),
    serchInput(),
    getProductLikeName(),
    getStandartLikeName(),
    getProductLikeType(),
    getStandartLikeType(),
    getAllStdType(),
    dataTermProduct(),
    dataProduce(),
    getConsumerLikeName(),
    consumerGetAdd(),
    createProduct(),
    buttonParamsSearch(),
    periodStart(),
    searchFunction(),
    checkDateWaranty(),
    clearModalWindow(),
    checkControl(),
    periodEnd(),
    checkRezControl(),
    getValueInputSearch()
}

//GET ROLE FOR SHOW MAGAZINE
function getRole(mag){
    mag = mag.replace('[','');
    mag = mag.replace(']','');
    var arr = mag.split(', ');
    $.each(arr, function (k, v) {
        if(v == 'ROLE_CONTROL') role = 'ROLE_CONTROL';
    });
}

function getAttachFile() {
    $("#list_uploaded_document").children(".file_upload").each(function () {
        attachDocs.push({
            idAttach: "0",
            name: $(this).attr("id"),
            link: $(this).attr("name")
        });
    });
    $("#list_uloaded_files").children(".file_upload").each(function () {
        attachFiles.push({
            idAttach: "0",
            name: $(this).attr("id"),
            link: $(this).attr("name")
        });
    });
}

function paginator(mgId) {
    wasCliked = false;
    deleteFile();
    magazine = mgId;
    switch (magazine){
        case 0 :
            if (pageNumber == null)pageNumber = 1;
            if(!search) getJoinIncomeProduct(pageNumber); else searchFunction();
            $('#prev').click(function () {
                if (pageNumber > 1) pageNumber = pageNumber - 1;
                if(!search) getJoinIncomeProduct(pageNumber); else searchFunction();
                $("#page_number").html(pageNumber);
            });
            $('#next').click(function () {
                pageNumber = pageNumber + 1;
                if(!search) getJoinIncomeProduct(pageNumber); else searchFunction();
                $("#page_number").html(pageNumber);
            });
            break;
        default :
            if (pageNumber == null)pageNumber = 1;
            if(!search) sortByMagazine(pageNumber, magazine); else searchFunction();
            $('#prev').click(function () {
                if (pageNumber > 1) {
                    pageNumber = pageNumber - 1;
                }
                if(!search) sortByMagazine(pageNumber, magazine); else searchFunction();
                $("#page_number").html(pageNumber);
            });
            $('#next').click(function () {
                pageNumber = pageNumber + 1;
                if(!search) sortByMagazine(pageNumber, magazine); else searchFunction();
                $("#page_number").html(pageNumber);
            });
    }
}

function clearModalWindow() {
    $(".close").click(function () {
        document.getElementById('open').style.display = "none";
        $(".datepicker_term").attr("disabled", false);
        preDeleteListFile();
        clear();
    });
    $("#cancel").click(function () {
        document.getElementById('open').style.display = "none";
        $(".datepicker_term").attr("disabled", false);
        preDeleteListFile();
        clear();
    });
    $("#createProduct").click(function () {
        document.getElementById('open').style.display = "none";
        //удаление родительского элемента с вложеными элементами
        $($("button.btn_del_upload_file").parents('p.file_upload').get(0)).remove();
    });
    $("#add").click(function () {
        document.getElementById('open').style.display = "block";
        $(".datepicker_get").val(getCurrentDate());
        $(".date_last_modify").val(getCurrentDate());
        enableAtributeInput();
        getAllMagazine();
        dataGetProduct();
        dataLastModify();
        getLocation();
        getAllMeasure()
    });
}

function preDeleteListFile() {
    var listFile = [];
    var jObject;
    if (attachDocs.length > 0) {
        $.each(attachDocs, function (i, val) {
            jObject = {name: "" + val.name + ""};
            listFile.push(jObject);
        });
        deleteListFile(listFile);
    }
    if (attachFiles.length > 0) {
        $.each(attachFiles, function (k, val) {
            jObject = {name: "" + val.name + ""};
            listFile.push(jObject);
        });
        deleteListFile(listFile);
    }
}

function deleteListFile(listFile) {
    $.ajax({
        url: '/QP/api/files/del_array_file',
        type: 'POST',
        data: JSON.stringify(listFile),
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function () {
            $(element.parent('p')).remove();
        },
        500: function () {
            alert("Ошибка удаления файла!");
        }
    });
}

function closeBrowser() {
    window.onbeforeunload = function () {
        preDeleteListFile();
    }
}

function clear() {
    $("#product_id").val('');
    $("#product_income_id").val('');
    $(".magazine_name").html('');
    $(".product_like_name_id").val('');
    $(".hvr_product_like_name_id").val('');
    $(".standart_like_name_id").val('');
    $(".hvr_standart_like_name_id").val('');
    $(".product_like_type_id").val('');
    $(".hvr_product_like_type_id").val('');
    $(".standart_like_type_id").val('');
    $(".hvr_standart_like_type_id").val('');
    $(".no_term").prop('checked', false);
    $(".datepicker_term").attr('disabled',false).val('Календарь');
    $(".datepicker").val('Календарь');
    $(".datepicker_get").val('');
    $(".number_part_id").val('');
    $(".consumer_id").val('');
    $(".hvr_consumer_id").val('');
    $("#doc_number").val('');
    $(".count_id").val('');
    $(".measure").html('');
    $("#fio_presenter").val('');
    $(".location").html('');
    $(".fio_last_modify").val('');
    $("#date_last_modify").val('');
    $(".fio_controller").val('');
    $(".arrangements").val('');
    $(".note").val('');
    $('#fileupload').val('');
    $("#list_uploaded_document").text('');
    $("#list_uloaded_files").text('');
    $(".radio_check_control").attr("checked",false);
}

function enableAtributeInput(){
    $(".magazine_name").attr("disabled", false);
    $(".product_like_name_id").attr("disabled", false);
    $(".standart_like_name_id").attr("disabled", false);
    $(".product_like_type_id").attr("disabled", false);
    $(".standart_like_type_id").attr("disabled", false);
    $(".datepicker").attr("disabled", false);
    $(".no_term").prop('disabled', false);
    //$(".datepicker_term").attr("disabled", false);
    $("#doc_number").attr("disabled", false);
    $(".number_part_id").attr("disabled", false);
    $(".consumer_id").attr("disabled", false);
    $(".count_id").attr("disabled", false);
    $(".measure").attr("disabled", false);
    $(".location").attr("disabled", false);
}

//GET PRODUCT BY ID
function getProduct(idProd) {
    document.getElementById('open').style.display = "block";
    $.ajax({
        url: '/QP/api/service/get_product_by_id',
        cache: false,
        data: 'id=' + idProd,
        dataType: 'json',
        success: function (data) {
            if(role == null) {
                $(".magazine_name").attr("disabled", true);
                $(".product_like_name_id").attr("disabled", true);
                $(".hvr_product_like_name_id").attr("disabled", true);
                $(".standart_like_name_id").attr("disabled", true);
                $(".hvr_standart_like_name_id").attr("disabled", true);
                $(".product_like_type_id").attr("disabled", true);
                $(".hvr_product_like_type_id").attr("disabled", true);
                $(".standart_like_type_id").attr("disabled", true);
                $(".hvr_standart_like_type_id").attr("disabled", true);
                $(".no_term").prop('disabled', true);
                $(".datepicker_term").attr("disabled", true);
                $(".datepicker").attr("disabled", true);
                $(".datepicker_get").attr("disabled", true);
                $(".number_part_id").attr("disabled", true);
                $(".consumer_id").attr("disabled", true);
                $(".hvr_consumer_id").attr("disabled", true);
                $("#doc_number").attr("disabled", true);
                $(".count_id").attr("disabled", true);
                $(".measure").attr("disabled", true);
                $(".location").attr("disabled", true);
            }
            $.each(data, function (index, product) {
                if (product !== null) {
                    $("#product_id").val(product.ProductId);
                    $("#product_income_id").val(product.IncomeId);
                    var mData = '';
                    $.each(product.Magazines, function (index, magazine) {
                        if (product.MagazineId == magazine.MagId) {
                            mData = mData + '<option selected value="' + magazine.MagId + '">' + magazine.MagName + '</option>';
                            $(".magazine_name_input").val(magazine.MagName);
                            $(".hvr_magazine_name_input").val(magazine.MagId);
                        } else {
                            mData = mData + '<option value="' + magazine.MagId + '">' + magazine.MagName + '</option>'
                        }
                    });
                    $(".magazine_name").html(mData);
                    $(".product_like_name_id").val(product.Name);
                    $(".hvr_product_like_name_id").val(product.IdName);
                    $(".standart_like_name_id").val(product.StdName);
                    $(".hvr_standart_like_name_id").val(product.StdNameId);
                    $(".product_like_type_id").val(product.Type);
                    $(".hvr_product_like_type_id").val(product.IdType);
                    $(".standart_like_type_id").val(product.StdType);
                    $(".hvr_standart_like_type_id").val(product.StdTypeId);
                    var data_term = getDateFromUnixTime(product.Waranty);
                    if(product.Waranty != 0) {
                        $(".no_term").prop('checked',false);
                        $(".datepicker_term").attr("disabled",true).val(data_term);
                    } else {
                        $(".no_term").prop('checked', true);
                        $(".datepicker_term").attr("disabled",true).val('');
                    }
                    $(".datepicker").val(product.ProduceDate);
                    $(".datepicker_get").val(product.DataPresenter);
                    $(".number_part_id").val(product.Lot);
                    $(".consumer_id").val(product.Consumer);
                    $(".hvr_consumer_id").val(product.ConsumerId);
                    var attachDocs = '';
                    $.each(product.AttachDocuments, function (index, file) {
                        attachDocs = attachDocs +
                            '<a class="td" ' +
                            'style="font-size:11px;" ' +
                            'title="Просмотреть" ' +
                            'href="/QP/api/files/get_file?file=' + file.DocumentName + '">' + file.DocumentName + '</a><br/>'
                    });
                    $("#doc_number").attr("disabled", true);
                    $("#list_uploaded_document").html(attachDocs);
                    $(".count_id").val(product.CountProduct);
                    var mesureData = '';
                    $.each(product.Measures, function (index, measure) {
                        if (product.Measure == measure.MeasureId) {
                            mesureData = mesureData + '<option selected value="' + measure.MeasureId + '" >' + measure.MeasureName + '</option>'
                            $(".impt_measure").val(measure.MeasureName);
                            $(".hvr_impt_measure").val(measure.MeasureId);
                        } else {
                            mesureData = mesureData + '<option value="' + measure.MeasureId + '">' + measure.MeasureName + '</option>'
                        }
                    });
                    $(".measure").html(mesureData);
                    $(".fio_presenter").val(product.FioPresenter);
                    var locationData = '';
                    $.each(product.Locations, function (index, location) {
                        if (product.Location == location.LocationId) {
                            locationData = locationData + '<option selected value="' + location.LocationId + '">' + location.LocationName + '</option>'
                            $(".impt_location").val(location.LocationName);
                            $(".hvr_impt_location").val(location.LocationId);
                        } else {
                            locationData = locationData + '<option value="' + location.LocationId + '">' + location.LocationName + '</option>'
                        }
                    });
                    $(".location").html(locationData);
                    $(".fio_last_modify").val(product.FioLastModify);
                    $(".date_last_modify").val(product.DateLastModify);
                    if(product.FioControler != "") $(".fio_controller").val(product.FioControler);
                    else $('.'+elem).val(user);
                    var attach = '';
                    $.each(product.AttachFiles, function(index, file){
                        attach = attach +
                            '<a class="td" ' +
                            'style="font-size:11px;" ' +
                            'title="Просмотреть" ' +
                            'href="/QP/api/files/get_file?file='+file.FileName+'">'+file.FileName+'</a><br/>'
                    });
                    $("#list_uloaded_files").html(attach);
                    $(".arrangements").val(product.Arrangaments);
                    $(".note").val(product.Note);
                    switch (product.ResultControl) {
                        case 2 :
                            $("#radio_no").prop("checked", true);
                            $("#radio_no_dis").prop("checked", true);
                            result_control = "2";
                            break;
                        case 1 :
                            $("#radio_yes").prop("checked", true);
                            $("#radio_yes_dis").prop("checked", true);
                            result_control = "1";
                            break;
                        default :
                            $(".radio_check_control").prop("checked", false);
                            result_control = "999";
                            break;
                    }
                } else {
                    alert("Ошибка получения информации о продукции!");
                }
            });
        }
    })
}

//LIST JOIN PRODUC INCOME
function getJoinIncomeProduct(pageNumber) {
    document.getElementById('loading_wrapper').style.display = "block";
    getCountNewProduct();
    closeBrowser();
    $.ajax({
        url: '/QP/api/service/join_product',
        cache: false,
        data: 'page=' + pageNumber,
        dataType: 'json',
        success: function (data) {
            document.getElementById('loading_wrapper').style.display = "none";
            var prData = '';
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
            //getUserApps();
        }
    })
}

//GET ALL PRODUCT INCOME BY MAGAZINE
function sortByMagazine(pageNumber, mgId) {
    document.getElementById('loading_wrapper').style.display = "block";
    getCountNewProduct();
    var data = {
        page: pageNumber,
        magazine: mgId
    };
    $.ajax({
        url: '/QP/api/service/sort_magazine',
        cache: false,
        data: data,
        dataType: 'json',
        success: function (data) {
            document.getElementById('loading_wrapper').style.display = "none";
            var prData = '';
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
            //getUserApps();
        }
    });
}

//GET ROLE FOR SHOW MAGAZINE
function getRoleForMagazines(mag){
    mag = mag.replace('[','');
    mag = mag.replace(']','');
    var arr = mag.split(', ');
    $.each(arr, function (k, v) {
        switch(v){
            case 'ROLE_METAL':
                mmm = 'Металл (черный и цветной)';
                break;
            case 'ROLE_SETKA':
                qqq = 'Сетка, лента, проволка';
                break;
            case 'ROLE_COMPL':
                yyy = 'Комплектующие';
                break;
            case 'ROLE_CHEMISTRY':
                eee = 'Химия, ГСМ';
                break;
            case 'ROLE_NONMETAL':
                rrr = 'Неметаллические материалы';
                break;
            case 'ROLE_METIZ':
                ttt = 'Метизы';
                break;
        }
    });
}

//LIST MAGAZINE
function getAllMagazine() {
    $.ajax({
        url: '/QP/api/service/all_magazine',
        cache: false,
        dataType: 'json',
        success: function (data) {
            var magData = magData + '<option value=' + null + '>Выбрать</option>';
            $.each(data, function (index, magaz) {
                if (magaz !== null) {
                    $("#hvr_magazine_name_input").val(magaz.MagazineId);
                    $(".magazine_name_input").val(magaz.MagazineName);
                    if(magaz.MagazineId == magazine) {
                        if (magaz.MagazineName == mmm)
                            magData = magData + '<option value=' + magaz.MagazineId + ' selected>' + magaz.MagazineName + '</option>';
                        if (magaz.MagazineName == qqq)
                            magData = magData + '<option value=' + magaz.MagazineId + ' selected>' + magaz.MagazineName + '</option>';
                        if (magaz.MagazineName == yyy)
                            magData = magData + '<option value=' + magaz.MagazineId + ' selected>' + magaz.MagazineName + '</option>';
                        if (magaz.MagazineName == eee)
                            magData = magData + '<option value=' + magaz.MagazineId + ' selected>' + magaz.MagazineName + '</option>';
                        if (magaz.MagazineName == rrr)
                            magData = magData + '<option value=' + magaz.MagazineId + ' selected>' + magaz.MagazineName + '</option>';
                        if (magaz.MagazineName == ttt)
                            magData = magData + '<option value=' + magaz.MagazineId + ' selected>' + magaz.MagazineName + '</option>';
                    } else {
                        if (magaz.MagazineName == mmm)
                            magData = magData + '<option value=' + magaz.MagazineId + '>' + magaz.MagazineName + '</option>';
                        if (magaz.MagazineName == qqq)
                            magData = magData + '<option value=' + magaz.MagazineId + '>' + magaz.MagazineName + '</option>';
                        if (magaz.MagazineName == yyy)
                            magData = magData + '<option value=' + magaz.MagazineId + '>' + magaz.MagazineName + '</option>';
                        if (magaz.MagazineName == eee)
                            magData = magData + '<option value=' + magaz.MagazineId + '>' + magaz.MagazineName + '</option>';
                        if (magaz.MagazineName == rrr)
                            magData = magData + '<option value=' + magaz.MagazineId + '>' + magaz.MagazineName + '</option>';
                        if (magaz.MagazineName == ttt)
                            magData = magData + '<option value=' + magaz.MagazineId + '>' + magaz.MagazineName + '</option>';
                    }
                } else {
                    magData = magData + '&nbsp;';
                }
            });
            $('.magazine_name').html(magData);
        }
    })
}

//LIST STANDART TYPE
function getAllStdType() {
    $.ajax({
        url: '/QP/api/service/all_std_type',
        cache: false,
        dataType: 'json',
        success: function (data) {
            var typeData = typeData + '<option value=' + null + '>Выбрать</option>';
            $.each(data, function (index, type) {
                if (type !== null) {
                    typeData = typeData + '<option value=' + type.stdId + '>' + type.stdName + '</option>';
                } else {
                    typeData = typeData + '&nbsp;';
                }
            });
            $('.std_type').html(typeData);
        }
    })
}

//LIST PRODUCT LIKE NAME
function getProductLikeName() {
    $('.product_like_name_id').autocomplete({
        source: function (request, response) {
            $.ajax({
                url: '/QP/api/service/product_like_name',
                dataType: 'json',
                data: {
                    name: request.term // поисковая фраза
                },
                success: function (data) {
                    if(data.length > 0) {
                        $('.product_like_name_id').css("color", "#000000");
                        response($.map(data, function (item) {
                            return {
                                id: item.likeId,
                                value: item.likeName
                            }
                        }));
                    } else {
                        alert("НЕТ В БАЗЕ АСПЕКТА!");
                        $('.product_like_name_id').val($('.product_like_name_id').val() + '').css("color", "red");
                        $('.hvr_product_like_name_id').val("");
                        $('.ui-widget-content').css("display","none");
                    }
                }
            });
        },
        select: function (event, ui) {
            $(".product_like_name_id").val(ui.item.value);
            $(".hvr_product_like_name_id").val(ui.item.id);
            return false;
        },
        minLength: 2 // начинать поиск
    });
}

//LIST PRODUCT LIKE TYPE
function getProductLikeType() {
    $('.product_like_type_id').autocomplete({
        source: function (request, response) {
            $.ajax({
                url: '/QP/api/service/product_like_type',
                dataType: 'json',
                data: {
                    name: request.term // поисковая фраза
                },
                success: function (data) {
                    if(data.length > 0) {
                        $('.product_like_type_id').css("color", "#000000");
                        response($.map(data, function (item) {
                            return {
                                id: item.likeId,
                                value: item.likeName
                            }
                        }));
                    } else {
                        alert("НЕТ В БАЗЕ АСПЕКТА!");
                        $('.product_like_type_id').val($('.product_like_type_id').val() + '').css("color", "red");
                        $('.hvr_product_like_type_id').val("");
                        $('.ui-widget-content').css("display","none");
                    }
                }
            });
        },
        select: function (event, ui) {
            $(".product_like_type_id").val(ui.item.value);
            $(".hvr_product_like_type_id").val(ui.item.id);
            return false;
        },
        minLength: 2 // начинать поиск
    });
}

//LIST STANDART LIKE NAME
function getStandartLikeName() {
    $('.standart_like_name_id').autocomplete({
        source: function (request, response) {
            $.ajax({
                url: '/QP/api/service/standart_like',
                dataType: 'json',
                data: {
                    name: request.term // поисковая фраза
                },
                success: function (data) {
                    if(data.length > 0) {
                        $('.standart_like_name_id').css("color", "#000000");
                        response($.map(data, function (item) {
                            return {
                                id: item.likeId,
                                value: item.likeName
                            }
                        }));
                    } else {
                        alert("НЕТ В БАЗЕ АСПЕКТА!");
                        $('.standart_like_name_id').val($('.standart_like_name_id').val() + '').css("color", "red");
                        $('.hvr_standart_like_name_id').val("");
                        $('.ui-widget-content').css("display","none");
                    }
                }
            });
        },
        select: function (event, ui) {
            $(".standart_like_name_id").val(ui.item.value);
            $(".hvr_standart_like_name_id").val(ui.item.id);
            return false;
        },
        minLength: 2 // начинать поиск
    });
}

//LIST STANDART LIKE TYPE
function getStandartLikeType() {
    $('.standart_like_type_id').autocomplete({
        source: function (request, response) {
            $.ajax({
                url: '/QP/api/service/standart_like',
                dataType: 'json',
                data: {
                    name: request.term // поисковая фраза
                },
                success: function (data) {
                    if(data.length > 0) {
                        $('.standart_like_type_id').css("color", "#000000");
                        response($.map(data, function (item) {
                            return {
                                id: item.likeId,
                                value: item.likeName
                            }
                        }));
                    } else {
                        alert("НЕТ В БАЗЕ АСПЕКТА!");
                        $('.standart_like_type_id').val($('.standart_like_type_id').val() + '').css("color", "red");
                        $('.hvr_standart_like_type_id').val("");
                        $('.ui-widget-content').css("display","none");
                    }
                }
            });
        },
        select: function (event, ui) {
            $(".standart_like_type_id").val(ui.item.value);
            $(".hvr_standart_like_type_id").val(ui.item.id);
            return false;
        },
        minLength: 2 // начинать поиск
    });
}

function getCurrentDate(){
    //GET CURRENT DATE
    var date = new Date();
    var year = date.getFullYear();
    var month = (1 + date.getMonth()).toString();
    month = month.length > 1 ? month : '0' + month;
    var day = date.getDate().toString();
    day = day.length > 1 ? day : '0' + day;
    return day + "." + month + "." + year;
}

function getDateFromUnixTime(d){
    var date = new Date(parseInt(d)*1000);
    var year = date.getFullYear();
    var month = (1 + date.getMonth()).toString();
    month = month.length > 1 ? month : '0' + month;
    var day = date.getDate().toString();
    day = day.length > 1 ? day : '0' + day;
    return day + "." + month + "." + year;
}

//DATA TERM PRODUCT
function dataTermProduct() {
    $(function () {
        $.datepicker.setDefaults(
            $.extend($.datepicker.regional['ru'])
        );
        $(".datepicker_term").datepicker();
    });
}

//DATA PRODUCE PRODUCT
function dataProduce() {
    $(function () {
        $.datepicker.setDefaults(
            $.extend($.datepicker.regional['ru'])
        );
        $(".datepicker").datepicker();
    });
}

//DATA GET PRODUCT
function dataGetProduct() {
    $("#datepicker_get").val(getCurrentDate());
}

//DATA GET LAST MODIFY PRODUCT
function dataLastModify() {
    $("#date_last_modify").val(getCurrentDate());
}

//LIST CONSUMER LIKE NAME
function getConsumerLikeName() {
    $('.consumer_id').autocomplete({
        source: function (request, response) {
            $.ajax({
                url: '/QP/api/service/consumer_like_name',
                dataType: 'json',
                data: {name: request.term},
                success: function (data) {
                    response($.map(data, function (item) {
                        return {
                            id: item.likeId,
                            value: item.likeName
                        }
                    }));
                },
            });
        },
        select: function (event, ui) {
            $(".consumer_id").val(ui.item.value);
            $(".hvr_consumer_id").val(ui.item.id);
            return false;
        },
        minLength: 2 // начинать поиск
    });
}

//ADD CONSUMER IN DB
function consumerSet() {
    var consumer = {consumer_id: $(".consumer_id").val()}
    $.ajax({
        url: '/QP/api/service/add_consumer',
        type: 'POST',
        data: JSON.stringify(consumer),
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function (newConsumer) {
            $('.hvr_consumer_id').val(newConsumer);
        },
        error: function (newConsumer) {
            alert("Ошибка дабавления производителя!");
        }
    });
}

function consumerGetAdd() {
    $('.consumer_id').focusout(function () {
        if ($('.consumer_id').val().length != 0) {
            consumerSet();
        }
    });
}

//LIST MEASURE
function getAllMeasure() {
    $.ajax({
        url: '/QP/api/service/all_measure',
        cache: false,
        dataType: 'json',
        success: function (data) {
            var magData = magData + '<option value=' + null + '>Выбрать</option>';
            $.each(data, function (index, measure) {
                if (measure !== null) {
                    magData = magData + '<option value=' + measure.MeasureId + '>' + measure.MeasureName + '</option>';
                } else {
                    magData = magData + '&nbsp;';
                }
            });
            $('.measure').html(magData);
        }
    })
}

//LIST LOCATION
function getLocation() {
    $.ajax({
        url: '/QP/api/service/location',
        cache: false,
        dataType: 'json',
        success: function (data) {
            var magData = magData + '<option value=' + null + '>Выбрать</option>';
            $.each(data, function (index, location) {
                if (location !== null) {
                    magData = magData + '<option value=' + location.LocationId + '>' + location.LocationName + '</option>';
                } else {
                    magData = magData + '&nbsp;';
                }
            });
            $('.location').html(magData);
        }
    })
}

//toogle checkbox(checked) and input(disable) where selest date term
function checkDateWaranty(){
    $(".no_term").on("click", function () {
        $(this).attr('checked', $(this).attr('checked'));
        $(".datepicker_term").attr("disabled",!$(".datepicker_term").attr('disabled')).val('');
    });
}

function checkControl(){
    $("input[name=check_control]:radio").change(function () {
        result_control = $(this).val();
    });
}

//CREATE PRODUCT (TABLE PRODUCT AND PRODUCT INCOME IN DB)
function createProduct() {
    $("#createProduct").click(function () {
        var mag;
        if($(".magazine_name").val() == null) mag = $(".hvr_magazine_name_input").val();
        else mag = $(".magazine_name").val();
        var measure;
        if($(".measure").val() == null) measure = $(".hvr_impt_measure").val();
        else measure = $(".measure").val();
        var loc;
        if($(".location").val() == null) loc = $(".hvr_impt_location").val();
        else loc = $(".location").val();
        var waranty;
        if($(".no_term").attr("checked")) waranty = '';
        else waranty = $(".datepicker_term").val();
        var product = {
            productId: $("#product_id").val(),
            productIncomeId: $("#product_income_id").val(),
            idMagazine: mag,
            idProdName: $(".hvr_product_like_name_id").val(),
            nameStandartProdName: $(".hvr_standart_like_name_id").val(),
            stdTypeIdName: $("#type_standart_by_name").val(),
            idProdType: $(".hvr_product_like_type_id").val(),
            nameStandartProdType: $(".hvr_standart_like_type_id").val(),
            stdTypeIdType: $("#type_standart_by_type").val(),
            waranty: waranty,
            produceDate: $(".datepicker").val(),
            datePresenter: $(".datepicker_get").val(),
            lot: $(".number_part_id").val(),
            consumerId: $(".hvr_consumer_id").val(),
            countProduct: $(".count_id").val(),
            measureId: measure,
            locationId: loc,
            fioPresenter: $(".fio_presenter").val(),
            fioLastModify: user,
            dateLastModify: getCurrentDate(),
            fioControler: $(".fio_controller").val(),
            arrangements: $(".arrangements").val(),
            note: $(".note").val(),
            resultControl: result_control,
            files: attachFiles,
            docs: attachDocs
        };
        $.ajax({
            url: '/QP/api/service/create_product',
            type: 'POST',
            data: JSON.stringify(product),
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            success: function (newProduct) {
                if (magazine == 0) {
                    $("#page_number").html(pageNumber);
                    $("#fileupload").val("");
                    $(".list_uloaded_files").children("p").remove();
                    $("#doc_number").val("");
                    $("#list_uploaded_document").children("p").remove();
                    if(!search) getJoinIncomeProduct(pageNumber); else searchFunction();
                } else {
                    $("#page_number").html(pageNumber);
                    $("#fileupload").val("");
                    $(".list_uloaded_files").children("p").remove();
                    $("#doc_number").val("");
                    $("#list_uploaded_document").children("p").remove();
                    if(!search) sortByMagazine(pageNumber, magazine); else searchFunction();
                };
                clear();
                attachDocs = [];
                attachFiles = [];
                result_control = "999";
                $(".datepicker_term").attr("disabled", false);
            },
            error: function (newProduct) {
                preDeleteListFile();
                $("#fileupload").val("");
                $('.list_uloaded_files').children("p").remove();
                $("#doc_number").val("");
                $("#list_uploaded_document").children("p").remove();
                alert("Ошибка дабавления продукции!");
            }
        });
    });
}

//GET UPLOAD PATH
function getUploadPath(){
    $.ajax({
        url: '/QP/api/files/path',
        success: function(data){
            path = data;
        },
        error: function () {
            alert("Ошибка получения адреса!");
        }
    });
}

//UPLOAD FILES
function uploadFiles() {
    var idElemUpload;
    var idListElemUpload;
    $('.upload').click(function() {
        idElemUpload = this.id;
        idListElemUpload = $('#'+idElemUpload).next().attr('id');
        getUploadPath();
        $('#'+idElemUpload).fileupload({
            add: function (e, data) {
                document.getElementById('loading_wrapper').style.display = "block";
                var jqXHR = data.submit()
                    .error(function (jqXHR, textStatus, errorThrown) {
                        $.each(data.files, function (index, file) {
                            document.getElementById('loading_wrapper').style.display = "none";
                            $('<p style="color: red;"/>').text("Файл " + file.name + " уже существует!")
                                .appendTo("#"+idListElemUpload);
                        });
                    })
                    .success(function (result, textStatus, jqXHR) {
                        document.getElementById('loading_wrapper').style.display = "none";
                        $.each(data.files, function (index, file) {
                            $('<p class="file_upload" id="' + file.name + '" name="' + path + file.name + '"/>')
                                .text(file.name)
                                .append("<button id='" + file.name + "' type='button' title='Удалить' class='btn_del_upload_file'" +
                                "style='border:none; background-color: #ffffff; font-size: 11px; color: red; cursor: pointer '>" +
                                "Удалить </button>")
                                .appendTo("#"+idListElemUpload);
                        });
                        attachDocs = [];//to empty the attachDocs array
                        attachFiles = [];//to empty the attachFiles array
                        getAttachFile();
                    });
            }
        });
    });
}

//DELETE ONE attach FILE
function deleteFile(){
    $( document ).on( "click", "button.btn_del_upload_file", function(event) {//получаем событие клик на несуществующюю кнопку
        var element = $(this);
        $.ajax({
            url: '/QP/api/files/del_file',
            data: 'file='+(event.target.id).trim(),
            cache: false,
            dataType: 'text',
            success: function () {
                $(element.parent('p')).remove();
            },
            error: function () {
                alert("Ошибка удаления файла!");
            }
        });
    });
}

function getCountNewProduct(){
    getCountNewMetall();
    getCountNewComplect();
    getCountNewHimiya();
    getCountNewNoMetall();
    getCountNewSetka();
    getCountNewMetiz();
}

function getCountNewMetall(){
    $.ajax({
        url: '/QP/api/service/count_new_product',
        data: 'magazine_id=' + 1,
        success: function(data){
            var count = '';
            if(data != 0){
                count = data;
                $('#metall').html(count);
            }else $('#metall').html(count);
        },
        error: function () {
            alert("Ошибка получения количества новой продукции по журналу Металлов!");
        }
    });
}

function getCountNewComplect(){
    $.ajax({
        url: '/QP/api/service/count_new_product',
        data: 'magazine_id=' + 2,
        success: function(data){
            var count = '';
            if(data != 0) {
                count = data;
                $('#complect').html(count);
            }else $('#complect').html(count);
        },
        error: function () {
            alert("Ошибка получения количества новой продукции по журналу Комплектующие!");
        }
    });
}

function getCountNewHimiya(){
    $.ajax({
        url: '/QP/api/service/count_new_product',
        data: 'magazine_id=' + 3,
        success: function(data){
            var count = '';
            if(data != 0) {
                count = data;
                $('#himiya').html(count);
            }else $('#himiya').html(count);
        },
        error: function () {
            alert("Ошибка получения количества новой продукции по журналу Химия, ГСМ!");
        }
    });
}

function getCountNewNoMetall(){
    $.ajax({
        url: '/QP/api/service/count_new_product',
        data: 'magazine_id=' + 4,
        success: function(data){
            var count = '';
            if(data != 0) {
                count = data;
                $('#no-metall').html(count);
            }else $('#no-metall').html(count);
        },
        error: function () {
            alert("Ошибка получения количества новой продукции по журналу Неметаллические материалы!");
        }
    });
}

function getCountNewSetka(){
    $.ajax({
        url: '/QP/api/service/count_new_product',
        data: 'magazine_id=' + 5,
        success: function(data){
            var count = '';
            if(data != 0) {
                count = data;
                $('#setka').html(count);
            }else $('#setka').html(count);

        },
        error: function () {
            alert("Ошибка получения количества новой продукции по журналу Сетка, лента, проволка!");
        }
    });
}

function getCountNewMetiz(){
    $.ajax({
        url: '/QP/api/service/count_new_product',
        data: 'magazine_id=' + 6,
        success: function(data){
            var count = '';
            if(data != 0) {
                count = data;
                $('#metiz').html(count);
            }else $('#metiz').html(count);
        },
        error: function () {
            alert("Ошибка получения количества новой продукции по журналу Метизы!");
        }
    });
}

