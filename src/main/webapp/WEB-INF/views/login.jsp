<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Вход</title>
    <link rel="stylesheet"
          href='<spring:url value="/resources/css/bootstrap.min.css"/>'/>
    <link rel="stylesheet"
          href='<spring:url value="/resources/css/custom.css"/>'/>
</head>

<body>
<div id="login-form">
    <spring:url value="/j_spring_security_check" var="url"/>
    <form:form role="form" action="${url}" method="POST">
        <legend>Качество продукции ПАО "ВАЗ"</legend>
        <c:if test="${param.error != null}">
            <div class="alert alert-danger">
                Ошибка входа.
                <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                    Причина: <c:out
                        value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
                </c:if>
            </div>
        </c:if>
        <div class="form-group">
            <label for="username">Имя пользователя</label>
            <input type="text"
                   id="username"
                   class="form-control"
                   required="required"
                   name="j_username"
                   placeholder="Имя пользователя">
        </div>
        <div class="form-group">
            <label for="password">Пароль</label>
            <input id="c"
                   type="password"
                   class="form-control"
                   required="required"
                   name="j_password"
                   placeholder="Пароль">
        </div>
        <button type="submit" class="btn btn-default">Войти</button>
    </form:form>
</div>

</body>
</html>
