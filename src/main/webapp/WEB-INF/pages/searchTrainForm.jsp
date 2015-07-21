<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-box">
    <h2> Search Train </h2>

    <form action="/SearchTrain" method="GET">
        <div class="form-row">
            <div class="form-label">
                <label for="departureStationId">From *</label>
            </div>
            <div class="form-field">
                <select class="input combobox" id="departureStationId" name="departureStationId">
                    <option value=""></option>
                    <c:forEach var="station" items="${stationSet}">
                        <option value="${station.id}">${station.name}</option>
                    </c:forEach>
                </select>
            </div>
            <span class="error-msg" style="display:none;">Please enter departure station</span>
        </div>

        <div class="form-row">
            <div class="form-label">
                <label for="arrivalStationId">To *</label>
            </div>
            <div class="form-field">
                <select class="input combobox" id="arrivalStationId" name="arrivalStationId">
                    <option value=""></option>
                    <c:forEach var="station" items="${stationSet}">
                        <option value="${station.id}">${station.name}</option>
                    </c:forEach>
                </select>
            </div>
            <span class="error-msg" style="display:none;">Please enter arrival station</span>
        </div>

        <div class="form-row">
            <div class="form-label">
                <label for="scheduleFromDate">Date btw: *</label>
            </div>
            <div class="form-field">
                <input type="text" class="input" id="scheduleFromDate" name="scheduleFromDate"/>
            </div>
            <span class="error-msg" style="display:none;">Please enter departure date from</span>
        </div>

        <div class="form-row">
            <div class="form-label">
                <label for="scheduleToDate">And *</label>
            </div>
            <div class="form-field">
                <input type="text" class="input" id="scheduleToDate" name="scheduleToDate"/>
            </div>
            <span class="error-msg" style="display:none;">Please enter departure date to</span>
        </div>
        <div class="form_submit">
            <input class="btn-submit" type="submit" value="Search">
        </div>
    </form>
</div>