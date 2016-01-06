<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Home</title>
    <spring:url value="/resources/js/viewFio.js" var="viewFio"/>
    <spring:url value="/resources/js/jquery-1.10.2.js" var="jquery"/>

    <script src="${jquery}"></script>
    <script src="${viewFio}"></script>
</head>
<body>
<script>
    getFioFromJson("/QPSec/api/userData/getFio/${username}", 'id01');
</script>

<a href="<c:url value="/j_spring_security_logout"/>"> Logout </a><br/>

<sec:authorize ifAnyGranted="ROLE_ADMIN">
    <h1>Только админы груп с ролями (ROLE_ADMIN) видят эту страницу.</h1><br/>
    <a href="admin"> Admin Home </a>
</sec:authorize>

<sec:authorize ifAnyGranted="ROLE_USER,ROLE_MET">
    <h1>Только пользователи груп с ролями (ROLE_USER,ROLE_MET) видят эту страницу.</h1><br/>

    <h2>
        <div id="id01"></div>
    </h2>

</sec:authorize>

<h1>Welcome, ${username}</h1>

<p>To string: ${toString}</p>

</body>
</html>