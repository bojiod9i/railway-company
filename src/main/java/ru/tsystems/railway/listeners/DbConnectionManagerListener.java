package ru.tsystems.railway.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tsystems.railway.utils.HibernateUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DbConnectionManagerListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbConnectionManagerListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOGGER.info("Initializing database");
        HibernateUtil.getSessionFactory();
        LOGGER.info("Database was initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.info("Closing database connection");
        HibernateUtil.shutdown();
        LOGGER.info("Database connection was closed");
    }
}
