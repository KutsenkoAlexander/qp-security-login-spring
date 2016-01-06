<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Доступ закрыт!</title>

    <link rel="stylesheet"
          href='<spring:url value="/resources/css/bootstrap.min.css"/>'/>
    <link rel="stylesheet"
          href='<spring:url value="/resources/css/custom.css"/>'/>

</head>
<body>

<div id="error-area">
    <h2>Доступ закрыт! Что - то пошло не так...</h2>

    <p class="alert">${msg}</p>
    <a href="<spring:url value="/login"/>" class="btn btn-info" role="button">${logIn}</a>
</div>

</body>
</html>
