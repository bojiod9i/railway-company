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

public class SearchTrainServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchTrainServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fromDateStr = req.getParameter("scheduleFromDate");
        String toDateStr = req.getParameter("scheduleToDate");
        String departureStationIdStr = req.getParameter("departureStationId");
        String arrivalStationIdStr = req.getParameter("arrivalStationId");

        if (fromDateStr == null || toDateStr == null || departureStationIdStr == null || arrivalStationIdStr == null) {
            LOGGER.error("Empty input argument(-s)");
            req.setAttribute("message", "Error: all input parameters must be specified!");
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/clientPage");
            dispatcher.forward(req, resp);
            return;
        }

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Long departureStationId = new Long(departureStationIdStr);
        Long arrivalStationId = new Long(arrivalStationIdStr);
        Date fromDate, toDate;
        try {
            fromDate = dateFormat.parse(fromDateStr);
            toDate = dateFormat.parse(toDateStr);
        } catch (ParseException e) {
            LOGGER.error("Illegal date argument", e);
            req.setAttribute("message", "Error: Illegal date format!");
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/clientPage");
            dispatcher.forward(req, resp);
            return;
        }
        Station departureStation = DaoFactory.getRailwayDao().getEntityById(Station.class, departureStationId);
        Station arrivalStation = DaoFactory.getRailwayDao().getEntityById(Station.class, arrivalStationId);
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(fromDate);
        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(toDate);
        Set<Train> trainSet = DaoFactory.getTrainDao().searchTrain(departureStation, arrivalStation,
                fromCalendar, toCalendar);
        req.setAttribute("departureStation", departureStation);
        req.setAttribute("arrivalStation", arrivalStation);
        req.setAttribute("trainSet", trainSet);
        RequestDispatcher disp = req.getServletContext().getRequestDispatcher("/clientPage");
        disp.forward(req, resp);
    }
}
