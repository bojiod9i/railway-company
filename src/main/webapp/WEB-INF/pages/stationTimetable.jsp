<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="infotabs" class="tab-panel">
    <ul>
        <li><a href="#stationTimetable"> Station Timetable </a>
        </li>
    </ul>

    <div class="form-box">
        <h2> Timetable of ${station.name} station </h2>
        <table class="infotable">
            <tr class="head">
                <td>Train№</td>
                <td>Departure time</td>
                <td>Arrival Station</td>
                <td>Arrival time</td>
                <td>Ticket</td>
            </tr>

            <c:forEach var="train" items="${trainSet}">
                <c:set var="nextStation" value="false"/>
                <c:forEach var="schedule" items="${train.schedules}">
                    <c:if test="${nextStation == true}">
                        <tr>
                            <td class="trainId">${train.id}</td>
                            <td>
                                <fmt:formatDate value="${depDate.time}" pattern="dd-MM-yyyy HH:mm"/>
                            </td>
                            <td>${schedule.station.name}</td>
                            <td>
                                <fmt:formatDate value="${schedule.departureDate.time}"
                                                pattern="dd-MM-yyyy HH:mm"/>
                            </td>
                            <td><input class="buy-btn" type="submit" value="Buy"></td>
                        </tr>
                    </c:if>
                    <c:if test="${schedule.station == station}">
                        <c:set var="depDate" value="${schedule.departureDate}"></c:set>
                        <c:set var="nextStation" value="true"/>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </table>
    </div>
</div>
