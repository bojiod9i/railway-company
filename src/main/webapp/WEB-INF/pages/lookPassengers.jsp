<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="form-box">
    <h2> Passengers of the train N ${lookingTrainId} </h2>
    <table class="infotable">
        <tr class="head">
            <td>Firstname</td>
            <td>Lastname</td>
            <td>Birthday</td>
        </tr>

        <c:forEach var="passenger" items="${passengerSet}">
            <tr>
                <td>${passenger.firstName}</td>
                <td>${passenger.lastName}</td>
                <td>
                    <fmt:formatDate value="${passenger.birthday}" pattern="dd-MM-yyyy"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

