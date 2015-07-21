package ru.tsystems.railway.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tsystems.railway.domain.Station;
import ru.tsystems.railway.domain.management.Employee;
import ru.tsystems.railway.utils.DaoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StationCreationServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(StationCreationServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stationName = req.getParameter("stationName");
        if (stationName == null) {
            LOGGER.error("Empty input argument(-s)");
            req.setAttribute("message", "Error: all input parameters must be specified!");
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/employeePage");
            dispatcher.forward(req, resp);
            return;
        }
        String message;
        if (DaoFactory.getStationDao().getStationByName(stationName) == null) {
            Station station = new Station(stationName);
            DaoFactory.getRailwayDao().save(station);
            Employee employee = (Employee) req.getSession().getAttribute("employee");
            LOGGER.info("Station " + stationName + " was registered by " + employee.getLogin());
            message = "Station " + stationName + " was successfully registered.";
        } else {
            message = "Station " + stationName + " is already registered.";
        }
        req.setAttribute("message", message);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/employeePage");
        dispatcher.forward(req, resp);
    }
}
