<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="dialog" id="buyTicketDialog" title="Booking ticket">
    <form action="/buyTicket" method="POST">
        <div class="form-box">
            <h2 id="booking-title"></h2>

            <input id='trainIdForBooking' type="hidden" name="trainId">

            <div class="form-row">
                <div class="form-label">
                    <label for="firstName">First Name: </label>
                </div>
                <div class="form-field">
                    <input type="text" class="input" id="firstName" name="firstName"/>
                    <span class="error-msg" style="display:none;">Please enter firstname</span>
                </div>
            </div>

            <div class="form-row">
                <div class="form-label">
                    <label for="lastName">Last name: </label>
                </div>
                <div class="form-field">
                    <input type="text" class="input" id="lastName" name="lastName"/>
                    <span class="error-msg" style="display:none;">Please enter lastname</span>
                </div>
            </div>

            <div class="form-row">
                <div class="form-label">
                    <label for="birthdayClient">Birthday </label>
                </div>
                <div class="form-field">
                    <input type="text" class="input frontElement" id="birthdayClient" name="birthdayClient"/>
                </div>
                <span class="error-msg" style="display:none;">Please enter birthday</span>
            </div>

            <div class="form_submit">
                <input class="btn-submit" type="submit" value="Book">
            </div>
        </div>
    </form>
</div>
