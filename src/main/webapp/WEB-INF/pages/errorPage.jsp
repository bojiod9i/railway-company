<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value='/resources/css/style.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/resources/css/errorPage.css'/>" rel="stylesheet"/>

    <title>ErrorPage</title>
</head>
<body>
<div class="error">
    <h1 class="error">Ooops, something happened wrong!</h1>
    <img src="<c:url value='/resources/images/error.png'/>"/>
</div>

</body>
</html>
