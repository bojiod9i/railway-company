package ru.tsystems.railway.dao;

public interface DaoFactory {

    RailwayDao getRailwayDao();

    TrainDao getTrainDao();

    StationDao getStationDao();

    TicketDao getTicketDao();

}
