<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-box">
    <h2> Station Timetable </h2>

    <form action="/stationTimetable" method="GET">
        <div class="form-row">
            <div class="form-label">
                <label for="stationInputId">Station *</label>
            </div>
            <div class="form-field">
                <select class="input combobox" id="stationInputId" name="stationInputId">
                    <option value=""></option>
                    <c:forEach var="station" items="${stationSet}">
                        <option value="${station.id}">${station.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <span class="error-msg" style="display:none;">Please enter station</span>

        <div class="form-row">
            <div class="form-label">
                <label for="timetableFromDate">Date btw: *</label>
            </div>
            <div class="form-field">
                <input type="text" class="input" id="timetableFromDate" name="timetableFromDate"/>
                <span class="error-msg" style="display:none;">Please enter date from</span>
            </div>
        </div>

        <div class="form-row">
            <div class="form-label">
                <label for="timetableToDate">And *</label>
            </div>
            <div class="form-field">
                <input type="text" class="input" id="timetableToDate" name="timetableToDate"/>
                <span class="error-msg" style="display:none;">Please enter date to</span>
            </div>
        </div>
        <div class="form_submit">
            <input class="btn-submit" type="submit" value="Show">
        </div>
    </form>
</div>
