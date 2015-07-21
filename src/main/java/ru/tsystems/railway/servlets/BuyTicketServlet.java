package ru.tsystems.railway.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tsystems.railway.domain.Passenger;
import ru.tsystems.railway.domain.Train;
import ru.tsystems.railway.exception.NoAvailableSeatsException;
import ru.tsystems.railway.exception.OutOfDateException;
import ru.tsystems.railway.exception.PassengerAlreadyRegisteredException;
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
import java.util.Date;

public class BuyTicketServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuyTicketServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String trainIdStr = req.getParameter("trainId");
        String birthdayStr = req.getParameter("birthdayClient");
        if (firstName == null || lastName == null || trainIdStr == null || birthdayStr == null) {
            LOGGER.error("Empty input argument(-s)");
            req.setAttribute("message", "Error: all input parameters must be specified!");
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/clientPage");
            dispatcher.forward(req, resp);
            return;
        }

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthdayDate;
        try {
            birthdayDate = dateFormat.parse(req.getParameter("birthdayClient"));
        } catch (ParseException e) {
            LOGGER.error("Illegal date argument", e);
            req.setAttribute("message", "Error: Illegal birthday format!");
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/clientPage");
            dispatcher.forward(req, resp);
            return;
        }

        Long trainId = new Long(req.getParameter("trainId"));

        Passenger passenger = DaoFactory.getPassengerDao().getPassengerByParams(firstName, lastName, birthdayDate);
        if (passenger == null) {
            passenger = new Passenger(firstName, lastName, birthdayDate);
            DaoFactory.getPassengerDao().save(passenger);
        }

        Train train = DaoFactory.getTrainDao().getEntityById(Train.class, trainId);
        String message;
        try {
            DaoFactory.getTicketDao().buyTicket(train, passenger);
            message = "The ticket by train N" + trainId + " was successfully booked.";
        } catch (NoAvailableSeatsException e) {
            message = "All ticket by train N " + trainId + " were sold. Operation was cancelled.";
        } catch (OutOfDateException e) {
            message = "The sale of tickets has ended.";
        } catch (PassengerAlreadyRegisteredException e) {
            message = "This passenger already was registered for this train.";
        }

        req.setAttribute("message", message);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/clientPage");
        dispatcher.forward(req, resp);
    }
}
