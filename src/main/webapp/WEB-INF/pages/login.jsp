<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta name="PageType" content="ContentPage"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="keywords" content="railway, trip, travel"/>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.3/themes/redmond/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script src="<c:url value='/resources/js/ui.js'/>"></script>

    <link href="<c:url value='/resources/css/style.css'/>" rel="stylesheet"/>

    <title> Railway Company </title>

</head>

<body>
<div class="wrapper">
    <div id="inputtabs" class="tab-panel">
        <!-- Tabs-->
        <ul>
            <li><a href="#tabs-1"> Login </a>
        </ul>

        <div id="tabs-1">
            <div class="form-box">
                <h2> Login </h2>

                <form action="/login" method="POST">
                    <div class="form-row">
                        <div class="form-label">
                            <label for="username">Username: </label>
                        </div>
                        <div class="form-field">
                            <input type="text" class="input" id="username" name="username"/>
                        </div>
                        <span class="error-msg" style="display:none;">Please enter username</span>
                    </div>

                    <div class="form-row">
                        <div class="form-label">
                            <label for="password">Password: </label>
                        </div>
                        <div class="form-field">
                            <input type="password" class="input" id="password" name="password"/>
                        </div>
                        <span class="error-msg" style="display:none;">Please enter password</span>
                    </div>

                    <div class="form_submit">
                        <input class="btn-submit" type="submit" value="Login">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <c:if test="${!empty message}">
        <div class="dialog" id="message-dialog" title="Message">
            <p>${message}</p>
        </div>
    </c:if>
</div>
</body>
</html>
