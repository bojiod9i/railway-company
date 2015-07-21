$(function () {
    var dialog;
    var scheduleIndex = 0;

    $(".buy-btn").on('click', function () {
        var trainId = this.closest('tr').getElementsByClassName("trainId")[0].textContent;
        document.getElementById('booking-title').textContent = 'Booking ticket by train N' + trainId;
        document.getElementById('trainIdForBooking').setAttribute('value', trainId);
        document.getElementById('buyTicketDialog').style.display = 'block';
        dialog.dialog("open");
    });

    $('form').on('submit', function () {
        var valid = true;
        var rowElems = this.getElementsByClassName('form-row');
        for (var i = 0; i < rowElems.length; i++) {
            var inputElems = rowElems[i].getElementsByTagName('input');
            for (var j = 0; j < inputElems.length; j++) {
                if (inputElems[j].type == "text") {
                    if (inputElems[j].value == "") {
                        valid = false;
                        var errorElems = rowElems[i].getElementsByClassName('error-msg');
                        for (var k = 0; k < errorElems.length; k++) {
                            errorElems[k].style.display = 'block';
                        }
                    }
                }
            }
        }
        return valid;
    });

    $("#timetableFromDate").datepicker({
        dateFormat: "dd/mm/yy",
        onClose: function (selectedDate) {
            $("#timetableToDate").datepicker("option", "minDate", selectedDate);
        }
    });
    $("#timetableToDate").datepicker({
        dateFormat: "dd/mm/yy",
        onClose: function (selectedDate) {
            $("#timetableFromDate").datepicker("option", "maxDate", selectedDate);
        }
    });

    $("#scheduleFromDate").datepicker({
        dateFormat: "dd/mm/yy",
        onClose: function (selectedDate) {
            $("#scheduleToDate").datepicker("option", "minDate", selectedDate);
        }
    });
    $("#scheduleToDate").datepicker({
        dateFormat: "dd/mm/yy",
        onClose: function (selectedDate) {
            $("#scheduleFromDate").datepicker("option", "maxDate", selectedDate);
        }
    });

    $("#birthdayClient").datepicker({
        dateFormat: "dd/mm/yy"
    });

    $(".tab-panel").tabs();

    dialog = $("#buyTicketDialog").dialog({
        autoOpen: false,
        width: 'auto',
        height: 'auto'
    });

    $('#message-dialog').dialog({
        width: 'auto',
        height: 'auto'
    });

    $('.combobox').combobox();

    $('.schedule-date-picker').datepicker({
        dateFormat: "dd/mm/yy"
    });

    $('#addSchedule').click(function () {
        copyElement();

        $('#stationId' + scheduleIndex).combobox();

        $('#scheduledate' + scheduleIndex).datepicker({
            dateFormat: "dd/mm/yy"
        });

        $('#scheduletime' + scheduleIndex).timepicker({});

        ++scheduleIndex;
    });

    function copyElement() {
        var original = document.getElementById('template_schedule');
        var clone = original.cloneNode(true);

        clone.id = "schedule" + scheduleIndex;

        function recurse(element, targetId) {
            for (var i = 0; i < element.childNodes.length; i++) {
                if (element.childNodes[i].id == targetId) {
                    element.childNodes[i].id = element.childNodes[i].id + scheduleIndex;
                    element.childNodes[i].name = element.childNodes[i].name + scheduleIndex;
                } else {
                    recurse(element.childNodes[i], targetId);
                }
            }
        }

        recurse(clone, 'stationId', false);
        recurse(clone, 'scheduledate', false);
        recurse(clone, 'scheduletime', false);

        clone.style.display = 'block';
        document.getElementById('add-schedules-form').appendChild(clone);
    }
});