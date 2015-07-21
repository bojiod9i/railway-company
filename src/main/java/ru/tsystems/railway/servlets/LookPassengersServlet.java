package ru.tsystems.railway.servlets;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tsystems.railway.domain.Passenger;
import ru.tsystems.railway.domain.Train;
import ru.tsystems.railway.utils.DaoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class LookPassengersServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(LookPassengersServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trainIdStr = req.getParameter("trainId");
        if (trainIdStr == null) {
            LOGGER.error("Empty input argument(-s)");
            req.setAttribute("message", "Error: all input parameters must be specified!");
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/employeePage");
            dispatcher.forward(req, resp);
            return;
        }
        Long trainId = new Long(trainIdStr);
        Train train = DaoFactory.getRailwayDao().getEntityById(Train.class, trainId);
        Set<Passenger> passengerSet = DaoFactory.getTrainDao().getPassengerByTrain(train);
        req.setAttribute("lookingTrainId", trainId);
        req.setAttribute("passengerSet", passengerSet);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/employeePage");
        dispatcher.forward(req, resp);
    }
}
