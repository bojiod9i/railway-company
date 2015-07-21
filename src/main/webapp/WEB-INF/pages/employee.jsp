<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script src="<c:url value='/resources/js/jquery.ui.timepicker.js'/>"></script>

    <link href="<c:url value='/resources/css/style.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/resources/css/combo.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/resources/css/jquery.ui.timepicker.css'/>" rel="stylesheet"/>

    <title> Railway Company </title>

</head>

<body>
<div class="wrapper">
    <div id="inputtabs" class="tab-panel">

        <!-- Tabs-->
        <ul>
            <li><a href="#tabs-1"> New Station </a>
            <li><a href="#tabs-2"> New Train </a>
        </ul>

        <!-- Content Tab 1 -->

        <div id="tabs-1">

            <div class="form-box">
                <h2> Station registration </h2>

                <form action="/NewStation" method="POST">
                    <div class="form-row">
                        <div class="form-label">
                            <label for="stationName">Station name</label>
                        </div>
                        <div class="form-field">
                            <input class="input" id="stationName" name="stationName"/>
                        </div>
                        <span class="error-msg" style="display:none;">Please enter station name</span>
                    </div>
                    <div class="form_submit">
                        <input class="btn-submit" type="submit" value="Register">
                    </div>
                </form>
            </div>
        </div>

        <!-- Content Tab 2 -->

        <div id="tabs-2">
            <form action="/NewTrain" method="POST">
                <div id="add-schedules-form" class="wide-form-box">
                    <h2> Train registration </h2>

                    <div class="form-row">
                        <div class="label">
                            <label>Seats </label>
                        </div>
                        <div class="form-field">
                            <input id="seats" type="text" class="input" name="seats"/>
                        </div>
                        <input id="addSchedule" type="button" value="Add schedule" class="simple-btn">
                        <span class="error-msg" style="display:none;">Please input seats</span>
                    </div>
                </div>
                <div class="form_submit">
                    <input class="btn-submit" type="submit" value="Save">
                </div>
            </form>
        </div>

    </div>

    <c:if test="${!empty trainSet || !empty lookingTrainId}">
        <div id="inputtabs" class="tab-panel">
            <ul>
                <c:if test="${!empty trainSet}">
                <li><a href="#allTrains"> All trains </a>
                    </c:if>
                    <c:if test="${!empty lookingTrainId}">
                <li><a href="#lookPassengers"> Passengers by train </a>
                    </c:if>
            </ul>
            <c:if test="${!empty trainSet}">
                <div id="allTrains">
                    <jsp:include page="lookAllTrains.jsp"/>
                </div>
            </c:if>
            <c:if test="${!empty lookingTrainId}">
                <div id="lookPassengers">
                    <jsp:include page="lookPassengers.jsp"/>
                </div>
            </c:if>
        </div>
    </c:if>

    <c:if test="${!empty message}">
        <div class="dialog" id="message-dialog" title="Message">
            <p>${message}</p>
        </div>
    </c:if>

    <div id="template_schedule" class="form-row" style="display: none">
        <div class="label">
            <label>Station *</label>
        </div>
        <div class="form-field">
            <select class="input" id="stationId" name="stationId">
                <option value=""></option>
                <c:forEach var="station" items="${stationSet}">
                    <option value="${station.id}">${station.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="label">
            <label>Date *</label>
        </div>
        <div class="form-field">
            <input type="text" class="input" maxlength="10" size="10"
                   id="scheduledate" name="scheduledate"/>
        </div>
        <div class="label">
            <label>Time </label>
        </div>
        <div class="form-field">
            <input type="text" class="input" maxlength="5" size="5" id="scheduletime"
                   name="scheduletime"/>
        </div>
        <span class="error-msg" style="display:none;">Please fill all fields</span>
    </div>
</div>
</body>
</html>
