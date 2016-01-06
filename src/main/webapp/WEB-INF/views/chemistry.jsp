<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<sec:authorize access="isAuthenticated()"
               ifAnyGranted="ROLE_CHEMISTRY,ROLE_COMPL,ROLE_METAL,ROLE_METIZ,ROLE_NONMETAL,ROLE_SETKA,ROLE_ALL,ROLE_CONTROL,ROLE_ADMIN">
    <!DOCTYPE html>
    <html>
    <link lang="ru">
    <meta charset="UTF-8">
    <title>Качество продукции</title>
        <%--resources paths css--%>
    <spring:url value="/resources/css/style.css" var="style"/>
    <spring:url value="/resources/css/jquery/jquery-ui.css" var="jqueryuicss"/>
        <%--resources paths js--%>
    <spring:url value="/resources/js/JQuery.js" var="jquery"/>
    <spring:url value="/resources/js/jquery-ui.js" var="jqueryuijs"/>
    <spring:url value="/resources/js/functions.js" var="functions"/>
    <spring:url value="/resources/js/search.js" var="search"/>
    <spring:url value="/resources/js/viewFio.js" var="viewfiojs"/>
    <spring:url value="/resources/js/jquery.fileupload.js" var="fileupload"/>
    <spring:url value="/resources/js/jquery.iframe-transport.js" var="iframe"/>
    <spring:url value="/resources/js/vendor/jquery.ui.widget.js" var="ui_widget"/>
        <%--images paths--%>
    <spring:url value="/resources/img/loading.gif" var="loadgif"/>
    <spring:url value="/resources/img/Book%20Shelf.png" var="journalpng"/>
    <spring:url value="/resources/img/add.png" var="imgadd"/>
    <spring:url value="/resources/img/user.png" var="imguser"/>
    <spring:url value="/resources/img/rewind.png" var="prev"/>
    <spring:url value="/resources/img/fast-forward.png" var="next"/>
        <%--views paths--%>
    <spring:url value="/all" var="v_all"/>
    <spring:url value="/metal" var="v_metal"/>
    <spring:url value="/complect" var="v_complect"/>
    <spring:url value="/himiya" var="v_chemistry"/>
    <spring:url value="/nemetal" var="v_nometal"/>
    <spring:url value="/setkaLentaProvoloka" var="v_setka"/>
    <spring:url value="/metiz" var="v_metiz"/>
        <%--response param from views--%>
    <link rel="stylesheet" href="${style}">
    <link rel="stylesheet" href="${jqueryuicss}">
    <script type="application/javascript" src="${jquery}"></script>
    <script type="text/javascript" src="${functions}"></script>
    <script type="text/javascript" src="${search}"></script>
    <script type="text/javascript" src="${jqueryuijs}"></script>
    <script type="text/javascript" src="${viewfiojs}"></script>
    <script type="text/javascript" src="${fileupload}"></script>
    <script type="text/javascript" src="${iframe}"></script>
    <script type="text/javascript" src="${ui_widget}"></script>
    </head>
    <body>
    <!--HEADER-->
    <header id="header" class="header">
        <div id="header_content" class="header_content">
            <a href="/QP/index"><h2>Поступающая продукция</h2></a>
            <!--SEARCH-->
            <div class="search_form">
                <input type="search" id="inpt_search" class="inpt_search" name="inpt_search" placeholder="Введите поисковый запрос"/>
                <input type="hidden" id="hvr_inpt_search" class="hvr_inpt_search" disabled/>
                <input type="button" id="btn_params" name="btn_params" title="Параметры поиска"/>
                <input type="button" id="btn_search" name="btn_search" title="Поиск"/>
                <!--MODAL PARAMS SEARCH-->
                <div class="params" id="params">
                    <div class="params_tuggle">
                        <label id="lbl_param_control"><input type="checkbox" id="param_control"> Контроль не пройден</label>
                        <fieldset>
                            <legend style="color: #111111;">Период</legend>
                            <table>
                                <tr>
                                    <td><p>от</p></td>
                                    <td><input id="period_start" type="text" class="inpt_param_search"/></td>
                                </tr>
                                <tr>
                                    <td><p>до</p></td>
                                    <td><input id="period_end" type="text" class="inpt_param_search"/></td>
                                </tr>
                            </table>
                        </fieldset>
                    </div>
                </div>
                <!--END MODAL PARAMS SEARCH-->
            </div>
            <!--NAVIGATION-->
            <nav>
                <ul class="nav">
                    <li><a href="" class="mark" title="Журналы"><img src="${journalpng}"/></a>
                        <ul class="sub-nav">
                            <li><a href="${v_all}" title="Вся продукция">Вся продукция</a></li>
                            <sec:authorize ifAnyGranted="ROLE_ALL,ROLE_CONTROL,ROLE_METAL">
                                <li><a href="${v_metal}" title="Металл (черный и цветной)">Металл (черный и цветной)</a></li>
                            </sec:authorize>
                            <sec:authorize ifAnyGranted="ROLE_ALL,ROLE_CONTROL,ROLE_COMPL">
                                <li><a href="${v_complect}" title="Комплектующие">Комплектующие</a></li>
                            </sec:authorize>
                            <sec:authorize ifAnyGranted="ROLE_ALL,ROLE_CONTROL,ROLE_CHEMISTRY">
                                <li><a href="${v_chemistry}" title="Химия, ГСМ">Химия, ГСМ</a></li>
                            </sec:authorize>
                            <sec:authorize ifAnyGranted="ROLE_ALL,ROLE_CONTROL,ROLE_NONMETAL">
                                <li><a href="${v_nometal}" title="Неметаллические материалы">Неметаллические
                                    материалы</a></li>
                            </sec:authorize>
                            <sec:authorize ifAnyGranted="ROLE_ALL,ROLE_CONTROL,ROLE_SETKA">
                                <li><a href="${v_setka}" title="Сетка, лента, проволка">Сетка, лента, проволка</a></li>
                            </sec:authorize>
                            <sec:authorize ifAnyGranted="ROLE_ALL,ROLE_CONTROL,ROLE_METIZ">
                                <li><a href="${v_metiz}" title="Метизы">Метизы</a></li>
                            </sec:authorize>
                        </ul>
                    </li>
                    <sec:authorize ifAnyGranted="ROLE_CHEMISTRY,ROLE_COMPL,ROLE_METAL,ROLE_METIZ,ROLE_NONMETAL,ROLE_SETKA">
                        <li>
                            <a href="#open" title="Добавить" id="add">
                                <img src="${imgadd}"/>
                            </a>
                        </li>
                    </sec:authorize>
                    <li><a href="" title="Личный кабинет"><img src="${imguser}"/></a>
                        <ul class="sub-nav">
                            <li><a class="login_l" href="<c:url value="/j_spring_security_logout"/>" title="Выйти">Выйти</a></li>
                            <div class="list_app"/>
                        </ul>
                        <script>
                            getFioFromJsonForLogin('/QP/userData/getFio/${username}', 'login_l');
                        </script>
                    </li>
                </ul>
            </nav>
        </div>
    </header>
    <!--CONTENT-->
    <div id="wrapper">
        <div id="content" class="content">
            <h2 id="title_page">Химия, ГСМ</h2>
            <div id="zizi"/>
            <!--TABLE PRODUCT-->
            <table border="0" cellpadding="0" cellspacing="0" id="main_table" class="main_table">
                <thead>
                <tr class="table_header">
                    <th class="title"><h5>Наименование продукции</h5></th>
                    <th class="lot"><h5>Номер партии</h5></th>
                    <th class="date_modify"><h5>Дата изменения</h5></th>
                    <th class="rez"><h5>Результат контроля</h5></th>
                    <th class="more"></th>
                </tr>
                </thead>
                <tbody id="table_content"></tbody>
            </table>
            <!--PAGINATION-->
            <div class="pagination_product">
                <button id="prev" class="paginator_btn"><img src="${prev}"/></button>
                <span id="page_number">1</span>
                <button id="next" class="paginator_btn"><img src="${next}"/></button>
            </div>
        </div>
        <!--MODAL WINDOW-->
        <div id='open' class='modalDialog'>
            <div>
                <div class="wrrap_modal_header">
                    <div class="wrrap_modal_header">
                        <div class="modal_header"/>
                    </div>
                </div>
                <div class="wrrap_close">
                    <a href='#close' title='Закрыть' class='close'>x</a>
                </div>
                <form id="create_product" enctype="multipart/form-data">
                    <table id="t_modal" class="t_modal">
                        <input type="hidden" id="product_id" disabled/>
                        <input type="hidden" id="product_income_id" disabled/>
                        <tr>
                            <td class="t_td_name"><p>Журнал</p></td>
                            <td class="t_td_input" id="t_td_input_mag">
                                <sec:authorize ifAnyGranted="ROLE_CHEMISTRY,ROLE_COMPL,ROLE_METAL,ROLE_METIZ,ROLE_NONMETAL,ROLE_SETKA">
                                    <select class="magazine_name"  />
                                </sec:authorize>
                                <sec:authorize ifAnyGranted="ROLE_CONTROL,ROLE_USER,ROLE_ADMIN,ROLE_ALL">
                                    <input type="text" class="magazine_name_input" disabled/>
                                    <input type="hidden" class="hvr_magazine_name_input" disabled/>
                                </sec:authorize>
                            </td>
                        </tr>
                        <tr>
                            <td class="t_td_name"><p>Наименование</p></td>
                            <td class="t_td_input">
                                <sec:authorize ifAnyGranted="ROLE_CHEMISTRY,ROLE_COMPL,ROLE_METAL,ROLE_METIZ,ROLE_NONMETAL,ROLE_SETKA">
                                    <input type="text" class="product_like_name_id"/>
                                    <input type="hidden" class="hvr_product_like_name_id"/>
                                </sec:authorize>
                                <sec:authorize ifAnyGranted="ROLE_CONTROL,ROLE_USER,ROLE_ADMIN,ROLE_ALL">
                                    <input type="text" class="product_like_name_id" disabled/>
                                    <input type="hidden" class="hvr_product_like_name_id" disabled/>
                                </sec:authorize>
                            </td>
                        </tr>
                        <tr>
                            <td class="t_td_name"><p>ГОСТ, ОСТ, ТУ, ДСТУ</p></td>
                            <td class="t_td_input">
                                <sec:authorize ifAnyGranted="ROLE_CHEMISTRY,ROLE_COMPL,ROLE_METAL,ROLE_METIZ,ROLE_NONMETAL,ROLE_SETKA">
                                    <input type="text" class="standart_like_name_id"/>
                                    <input type="hidden" class="hvr_standart_like_name_id"/>
                                </sec:authorize>
                                <sec:authorize ifAnyGranted="ROLE_CONTROL,ROLE_USER,ROLE_ADMIN,ROLE_ALL">
                                    <input type="text" class="standart_like_name_id" disabled/>
                                    <input type="hidden" class="hvr_standart_like_name_id" disabled/>
                                </sec:authorize>
                            </td>
                        </tr>
                        <tr>
                            <td class="t_td_name"><p>Марка, тип</p></td>
                            <td class="t_td_input">
                                <sec:authorize ifAnyGranted="ROLE_CHEMISTRY,ROLE_COMPL,ROLE_METAL,ROLE_METIZ,ROLE_NONMETAL,ROLE_SETKA">
                                    <input type="text" class="product_like_type_id"/>
                                    <input type="hidden" class="hvr_product_like_type_id"/>
                                </sec:authorize>
                                <sec:authorize ifAnyGranted="ROLE_CONTROL,ROLE_USER,ROLE_ADMIN,ROLE_ALL">
                                    <input type="text" class="product_like_type_id" disabled/>
                                    <input type="hidden" class="hvr_product_like_type_id" disabled/>
                                </sec:authorize>
                            </td>
                        </tr>
                        <tr>
                            <td class="t_td_name"><p>ГОСТ, ОСТ, ТУ, ДСТУ</p></td>
                            <td class="t_td_input">
                                <sec:authorize ifAnyGranted="ROLE_CHEMISTRY,ROLE_COMPL,ROLE_METAL,ROLE_METIZ,ROLE_NONMETAL,ROLE_SETKA">
                                    <input type="text" class="standart_like_type_id"/>
                                    <input type="hidden" class="hvr_standart_like_type_id"/>
                                </sec:authorize>
                                <sec:authorize ifAnyGranted="ROLE_CONTROL,ROLE_USER,ROLE_ADMIN,ROLE_ALL,ROLE_ALL">
                                    <input type="text" class="standart_like_type_id" disabled/>
                                    <input type="hidden" class="hvr_standart_like_type_id" disabled/>
                                </sec:authorize>
                            </td>
                        </tr>
                        <tr>
                            <td class="t_td_name"><p>Дата изготовления (дд.мм.гггг)</p></td>
                            <td class="t_td_input">
                                <sec:authorize ifAnyGranted="ROLE_CHEMISTRY,ROLE_COMPL,ROLE_METAL,ROLE_METIZ,ROLE_NONMETAL,ROLE_SETKA">
                                    <input class="datepicker" type="button"/>
                                </sec:authorize>
                                <sec:authorize ifAnyGranted="ROLE_CONTROL,ROLE_USER,ROLE_ADMIN,ROLE_ALL">
                                    <input class="datepicker" type="button" disabled/>
                                </sec:authorize>
                            </td>
                        </tr>
                        <tr>
                            <td class="t_td_name"><p>Гарантийный срок (дд.мм.гггг)</p></td>
                            <td class="t_td_input_term">
                                <sec:authorize ifAnyGranted="ROLE_CHEMISTRY,ROLE_COMPL,ROLE_METAL,ROLE_METIZ,ROLE_NONMETAL,ROLE_SETKA">
                                    <input class="datepicker_term" type="button"/>
                                    <label><input type="checkbox" class="no_term"> Без гарантийного срока</label>
                                </sec:authorize>
                                <sec:authorize ifAnyGranted="ROLE_CONTROL,ROLE_USER,ROLE_ADMIN,ROLE_ALL">
                                    <input class="datepicker_term" type="button" disabled/>
                                    <label><input type="checkbox" class="no_term" disabled> Без гарантийного срока</label>
                                </sec:authorize>
                            </td>
                        </tr>
                        <tr>
                            <td class="t_td_name"><p>Дата предьявления (дд.мм.гггг)</p></td>
                            <td class="t_td_input">
                                <sec:authorize ifAnyGranted="ROLE_CHEMISTRY,ROLE_COMPL,ROLE_METAL,ROLE_METIZ,ROLE_NONMETAL,ROLE_SETKA">
                                    <input class="datepicker_get" type="text" disabled/>
                                </sec:authorize>
                                <sec:authorize ifAnyGranted="ROLE_CONTROL,ROLE_USER,ROLE_ADMIN,ROLE_ALL">
                                    <input class="datepicker_get" type="text" disabled/>
                                </sec:authorize>
                            </td>
                        </tr>
                        <tr>
                            <td class="t_td_name"><p>№ партии</p></td>
                            <td class="t_td_input">
                                <sec:authorize ifAnyGranted="ROLE_CHEMISTRY,ROLE_COMPL,ROLE_METAL,ROLE_METIZ,ROLE_NONMETAL,ROLE_SETKA">
                                    <input type="text" class="number_part_id"/>
                                </sec:authorize>
                                <sec:authorize ifAnyGranted="ROLE_CONTROL,ROLE_USER,ROLE_ADMIN,ROLE_ALL">
                                    <input type="text" class="number_part_id" disabled/>
                                </sec:authorize>
                            </td>
                        </tr>
                        <tr>
                            <td class="t_td_name"><p>Поставщик</p></td>
                            <td class="t_td_input">
                                <sec:authorize ifAnyGranted="ROLE_CHEMISTRY,ROLE_COMPL,ROLE_METAL,ROLE_METIZ,ROLE_NONMETAL,ROLE_SETKA">
                                    <input type="text" class="consumer_id"/>
                                    <input type="hidden" class="hvr_consumer_id"/>
                                </sec:authorize>
                                <sec:authorize ifAnyGranted="ROLE_CONTROL,ROLE_USER,ROLE_ADMIN,ROLE_ALL">
                                    <input type="text" class="consumer_id" disabled/>
                                    <input type="hidden" class="hvr_consumer_id" disabled/>
                                </sec:authorize>
                            </td>
                        </tr>
                        <tr>
                            <td class="t_td_name"><p>Сопроводительный документ</p></td>
                            <td class="t_td_input">
                                <sec:authorize ifAnyGranted="ROLE_CHEMISTRY,ROLE_COMPL,ROLE_METAL,ROLE_METIZ,ROLE_NONMETAL,ROLE_SETKA">
                                    <input id="doc_number" class="upload" type="file" name="file" data-url="api/files/upload" multiple/>
                                </sec:authorize>
                                <sec:authorize ifAnyGranted="ROLE_CONTROL,ROLE_USER,ROLE_ADMIN,ROLE_ALL">
                                </sec:authorize>
                                <div id="list_uploaded_document"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="t_td_name"><p>Количество продукции</p></td>
                            <td class="t_td_input_count">
                                <sec:authorize ifAnyGranted="ROLE_CHEMISTRY,ROLE_COMPL,ROLE_METAL,ROLE_METIZ,ROLE_NONMETAL,ROLE_SETKA">
                                    <input type="text" class="count_id"/>
                                    <select class="measure"></select>
                                </sec:authorize>
                                <sec:authorize ifAnyGranted="ROLE_CONTROL,ROLE_USER,ROLE_ADMIN,ROLE_ALL">
                                    <input type="text" class="count_id" style="width: 46%; display: inline-block;" disabled/>
                                    <input type="text" class="impt_measure" style="width: 46%; display: inline-block;" disabled/>
                                    <input type="hidden" class="hvr_impt_measure" disabled/>
                                </sec:authorize>
                            </td>
                        </tr>
                        <tr>
                            <td><p>Место нохождения продукции</p></td>
                            <td class="t_td_input">
                                <sec:authorize ifAnyGranted="ROLE_CHEMISTRY,ROLE_COMPL,ROLE_METAL,ROLE_METIZ,ROLE_NONMETAL,ROLE_SETKA">
                                    <select class="location"></select>
                                </sec:authorize>
                                <sec:authorize ifAnyGranted="ROLE_CONTROL,ROLE_USER,ROLE_ADMIN,ROLE_ALL">
                                    <input type="text" class="impt_location" disabled/>
                                    <input type="hidden" class="hvr_impt_location" disabled/>
                                </sec:authorize>
                            </td>
                        </tr>
                        <tr>
                            <td><p>ФИО предъявителя</p></td>
                            <td class="t_td_input">
                                <input type="text" class="fio_presenter" disabled/>
                                <sec:authorize ifAnyGranted="ROLE_CHEMISTRY,ROLE_COMPL,ROLE_METAL,ROLE_METIZ,ROLE_NONMETAL,ROLE_SETKA">
                                    <script>
                                        getFioFromJson('/QP/userData/getFio/${username}', 'fio_presenter')
                                    </script>
                                </sec:authorize>
                            </td>
                        </tr>
                        <tr>
                            <td><p>Автор последнего изменения</p></td>
                            <td class="t_td_input">
                                <input type="text" class="fio_last_modify" disabled/>
                                <script>
                                    getFioFromJson('/QP/userData/getFio/${username}', 'fio_last_modify');
                                </script>
                            </td>
                        </tr>
                        <tr>
                            <td><p>Дата последнего изменения</p></td>
                            <td class="t_td_input">
                                <input type="text" class="date_last_modify" disabled/>
                            </td>
                        </tr>
                        <tr>
                            <td><p>ФИО контролера</p></td>
                            <td class="t_td_input">
                                <input type="text" class="fio_controller" disabled/>
                                <sec:authorize ifAnyGranted="ROLE_CONTROL">
                                    <script>
                                        getFioFromJsonControler('/QP/userData/getFio/${username}', 'fio_controller');
                                    </script>
                                </sec:authorize>
                            </td>
                        </tr>
                        <tr>
                            <td style="vertical-align: top;"><p>Результат входного контроля</p></td>
                            <td class="t_td_input_control">
                                <sec:authorize ifAnyGranted="ROLE_CONTROL">
                                    <label><input type="radio" name="check_control" value="1" class="radio_check_control" id="radio_yes"> Пройден</label>
                                    <label><input type="radio" name="check_control" value="2" class="radio_check_control" id="radio_no"> Не пройден</label>
                                </sec:authorize>
                                <sec:authorize ifAnyGranted="ROLE_CHEMISTRY,ROLE_COMPL,ROLE_METAL,ROLE_METIZ,ROLE_NONMETAL,ROLE_SETKA,ROLE_ALL,ROLE_ADMIN">
                                    <label><input type="radio" name="check_control" value="1" class="radio_check_control" id="radio_yes_dis" disabled/> Пройден</label>
                                    <label><input type="radio" name="check_control" value="2" class="radio_check_control" id="radio_no_dis" disabled/> Не пройден</label>
                                </sec:authorize>
                            </td>
                        </tr>
                        <tr>
                            <td style="vertical-align: top;"><p>Заключение входного контроля</p></td>
                            <td class="t_td_input">
                                <sec:authorize ifAnyGranted="ROLE_CHEMISTRY,ROLE_COMPL,ROLE_METAL,ROLE_METIZ,ROLE_NONMETAL,ROLE_SETKA,ROLE_ALL,ROLE_ADMIN">
                                </sec:authorize>
                                <sec:authorize ifAnyGranted="ROLE_CONTROL">
                                    <input id="fileupload" class="upload" type="file" name="file" data-url="api/files/upload" multiple/>
                                </sec:authorize>
                                <div id="list_uloaded_files"/>
                            </td>
                        </tr>
                        <tr>
                            <td><p>Принятые меры</p></td>
                            <td class="t_td_input">
                                <sec:authorize ifAnyGranted="ROLE_CHEMISTRY,ROLE_COMPL,ROLE_METAL,ROLE_METIZ,ROLE_NONMETAL,ROLE_SETKA,ROLE_ALL,ROLE_ADMIN">
                                    <input type="text" class="arrangements" disabled/>
                                </sec:authorize>
                                <sec:authorize ifAnyGranted="ROLE_CONTROL">
                                    <input type="text" class="arrangements"/>
                                </sec:authorize>
                            </td>
                        </tr>
                        <tr>
                            <td><p>Примечание</p></td>
                            <td class="t_td_input">
                                <sec:authorize ifAnyGranted="ROLE_CHEMISTRY,ROLE_COMPL,ROLE_METAL,ROLE_METIZ,ROLE_NONMETAL,ROLE_SETKA,ROLE_ALL,ROLE_ADMIN">
                                    <input type="text" class="note" disabled/>
                                </sec:authorize>
                                <sec:authorize ifAnyGranted="ROLE_CONTROL">
                                    <input type="text" class="note"/>
                                </sec:authorize>
                            </td>
                        </tr>
                    </table>
                    <div class="pag_product">
                        <a href='#cancel' title='Закрыть' class="paginator_btn" id="cancel">Отмена</a>
                        <a href='#save' title='Сохранить' class="paginator_btn" id="createProduct">Сохранить</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!--LOADING-->
    <div id="loading_wrapper">
        <p>Загрузка <img src="${loadgif}"/></p>
    </div>
    <script type="text/javascript">$(document).ready(
            paginator(3),
            getRoleForMagazines('${GrantedAuthorities}'),
            getRole('${GrantedAuthorities}'),
            uploadAll()
    )
    </script>
    </body>
    </html>
</sec:authorize>
