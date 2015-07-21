package ru.tsystems.railway.utils;

import ru.tsystems.railway.dao.*;
import ru.tsystems.railway.dao.impl.*;

public class DaoFactory {

    private DaoFactory() {
    }

    public static RailwayDao getRailwayDao() {
        return new RailwayDaoImpl();
    }

    public static TrainDao getTrainDao() {
        return new TrainDaoImpl();
    }

    public static StationDao getStationDao() {
        return new StationDaoImpl();
    }

    public static TicketDao getTicketDao() {
        return new TicketDaoImpl();
    }

    public static PassengerDao getPassengerDao() {
        return new PassengerDaoImpl();
    }

    public static EmployeeDao getEmployeeDao() {
        return new EmployeeDaoImpl();
    }
}
