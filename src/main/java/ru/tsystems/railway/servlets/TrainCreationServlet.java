package ru.tsystems.railway.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tsystems.railway.dao.RailwayDao;
import ru.tsystems.railway.domain.Schedule;
import ru.tsystems.railway.domain.Station;
import ru.tsystems.railway.domain.Train;
import ru.tsystems.railway.domain.management.Employee;
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

public class TrainCreationServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchTrainServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String seatsStr = req.getParameter("seats");
        if (seatsStr == null) {
            LOGGER.error("Empty input argument(-s)");
            req.setAttribute("message", "Error: all input parameters must be specified!");
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/employeePage");
            dispatcher.forward(req, resp);
            return;
        }

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Integer seats = new Integer(seatsStr);
        Train train = new Train(seats);
        RailwayDao dao = DaoFactory.getRailwayDao();
        dao.save(train);
        for (int i = 0; req.getParameter("stationId" + i) != null; ++i) {
            Long stationId = new Long(req.getParameter("stationId" + i));
            String dateString = req.getParameter("scheduledate" + i);
            String timeString = req.getParameter("scheduletime" + i);
            if (dateString == null || timeString == null) {
                LOGGER.error("Empty input argument(-s)");
                req.setAttribute("message", "Error: all input parameters must be specified!");
                RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/employeePage");
                dispatcher.forward(req, resp);
                return;
            }
            Calendar departureDate = Calendar.getInstance();
            try {
                departureDate.setTime(dateFormat.parse(dateString + ' ' + timeString));
            } catch (ParseException e) {
                LOGGER.error("Illegal date argument", e);
                req.setAttribute("message", "Error: Illegal date format!");
                RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/employeePage");
                dispatcher.forward(req, resp);
                return;
            }
            Schedule schedule = new Schedule();
            schedule.setTrain(train);
            schedule.setStation(dao.getEntityById(Station.class, stationId));
            schedule.setDepartureDate(departureDate);
            dao.save(schedule);
        }
        Employee employee = (Employee) req.getSession().getAttribute("employee");
        LOGGER.info("Station " + train.getId() + " was registered by " + employee.getLogin());
        req.setAttribute("message", "Train was successfully registered.");
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/employeePage");
        dispatcher.forward(req, resp);
    }
}
