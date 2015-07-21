<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="infotabs" class="tab-panel">
    <ul>
        <li><a href="#timetableBtwStations"> Timetable </a>
        </li>
    </ul>

    <div class="form-box">
        <h2> Timetable ${departureStation.name} -> ${arrivalStation.name} </h2>
        <table class="infotable">
            <tr class="head">
                <td>Train№</td>
                <td>Departure time</td>
                <td>Arrival time</td>
                <td>Ticket</td>
            </tr>

            <c:forEach var="train" items="${trainSet}">
                <tr>
                    <td class="trainId">${train.id}</td>
                    <c:forEach var="departureSch" items="${train.schedules}">
                        <c:if test="${departureSch.station == departureStation}">
                            <td>
                                <fmt:formatDate value="${departureSch.departureDate.time}"
                                                pattern="dd-MM-yyyy HH:mm"/>
                            </td>
                        </c:if>
                    </c:forEach>
                    <c:forEach var="arrivalSch" items="${train.schedules}">
                        <c:if test="${arrivalSch.station == arrivalStation}">
                            <td>
                                <fmt:formatDate value="${arrivalSch .departureDate.time}"
                                                pattern="dd-MM-yyyy HH:mm"/>
                            </td>
                        </c:if>
                    </c:forEach>
                    <td><input class="buy-btn" type="submit" value="Buy"></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>