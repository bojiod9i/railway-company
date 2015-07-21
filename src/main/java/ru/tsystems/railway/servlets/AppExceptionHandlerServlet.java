package ru.tsystems.railway.servlets;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppExceptionHandlerServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppExceptionHandlerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/pages/errorPage.jsp");
        dispatcher.forward(req, resp);
    }

    private void processError(HttpServletRequest req) {
        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        LOGGER.error("Internal error with code status " + statusCode, throwable);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req);
    }
}
