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
    <script src="<c:url value='/resources/js/combo.js'/>"></script>
    <script src="<c:url value='/resources/js/ui.js'/>"></script>

    <link href="<c:url value='/resources/css/style.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/resources/css/combo.css'/>" rel="stylesheet"/>

    <title> Railway Company </title>

</head>

<body>
<div class="wrapper">
    <div id="inputtabs" class="tab-panel">
        <!-- Tabs-->
        <ul>
            <li><a href="#tabs-1"> Train </a>
            <li><a href="#tabs-2"> Station </a>
        </ul>

        <!-- Content Tab 1 -->

        <div id="tabs-1">
            <jsp:include page="searchTrainForm.jsp"/>
        </div>

        <!-- Content Tab 2 -->

        <div id="tabs-2">
            <jsp:include page="lookStationForm.jsp"/>
        </div>
    </div>

    <c:if test="${!empty departureStation && !empty arrivalStation}">
        <jsp:include page="timetableBtwStations.jsp"/>
    </c:if>

    <c:if test="${!empty station}">
        <jsp:include page="stationTimetable.jsp"/>
    </c:if>

    <jsp:include page="buyTicket.jsp"/>

    <c:if test="${!empty message}">
        <div class="dialog" id="message-dialog" title="Message">
            <p>${message}</p>
        </div>
    </c:if>

</div>
</body>
</html>
