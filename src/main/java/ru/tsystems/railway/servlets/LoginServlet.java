package ru.tsystems.railway.servlets;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tsystems.railway.domain.management.Employee;
import ru.tsystems.railway.utils.DaoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || password == null) {
            LOGGER.error("Empty input argument(-s)");
            req.setAttribute("message", "Error: all input parameters must be specified!");
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/loginPage");
            dispatcher.forward(req, resp);
            return;
        }

        Employee employee = DaoFactory.getEmployeeDao().login(username, password);
        if (employee != null) {
            req.getSession().setAttribute("employee", employee);
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/employeePage");
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("message", "Invalid username/password!");
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/loginPage");
            dispatcher.forward(req, resp);
        }

    }
}
