package ru.tsystems.railway.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tsystems.railway.domain.Station;
import ru.tsystems.railway.utils.DaoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class ClientPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Station> stationSet = DaoFactory.getStationDao().list(Station.class);
        req.setAttribute("stationSet", stationSet);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/pages/client.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
