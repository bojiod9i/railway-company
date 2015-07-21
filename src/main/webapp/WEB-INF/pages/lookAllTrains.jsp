<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="form-box">
    <h2> List of entity </h2>
    <table class="infotable">
        <tr class="head">
            <td>Train№</td>
            <td>Time</td>
            <td>Station</td>
            <td>Seats</td>
            <td>Passengers</td>
        </tr>

        <c:forEach var="train" items="${trainSet}">
            <c:forEach var="schedule" items="${train.schedules}">
                <tr>
                    <td class="trainId">${train.id}</td>
                    <td>
                        <fmt:formatDate value="${schedule.departureDate.time}"
                                        pattern="dd-MM-yyyy HH:mm"/>
                    </td>
                    <td>${schedule.station.name}</td>
                    <td>
                            ${train.seats}
                    </td>
                    <td>
                        <form action="/LookPassengersByTrain" method="POST">
                            <input type="hidden" name="trainId" value="${train.id}">
                            <input class="simple-btn" type="submit" value="Look">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </c:forEach>
    </table>
</div>


