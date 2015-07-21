package ru.tsystems.railway.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tsystems.railway.domain.Station;
import ru.tsystems.railway.domain.Train;
import ru.tsystems.railway.utils.DaoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class StationTimetableServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(StationTimetableServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stationInputIdStr = req.getParameter("stationInputId");
        String fromDateStr = req.getParameter("timetableFromDate");
        String toDateStr = req.getParameter("timetableToDate");

        if (stationInputIdStr == null || fromDateStr == null || toDateStr == null) {
            LOGGER.error("Empty input argument(-s)");
            req.setAttribute("message", "Error: all input parameters must be specified!");
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/clientPage");
            dispatcher.forward(req, resp);
            return;
        }

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Long stationId = new Long(stationInputIdStr);
        Date fromDate, toDate;
        try {
            fromDate = dateFormat.parse(fromDateStr);
            toDate = dateFormat.parse(toDateStr);
        } catch (ParseException e) {
            LOGGER.error("Illegal date argument", e);
            req.setAttribute("message", "Error: Illegal birthday format!");
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/clientPage");
            dispatcher.forward(req, resp);
            return;
        }

        Station station = DaoFactory.getStationDao().getEntityById(Station.class, stationId);
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(fromDate);
        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(toDate);
        Set<Train> trainSet = DaoFactory.getStationDao().getTrainsByDate(station, fromCalendar, toCalendar);

        req.setAttribute("station", station);
        req.setAttribute("trainSet", trainSet);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/clientPage");
        dispatcher.forward(req, resp);
    }
}
