package ru.tsystems.railway.dao.impl;


import ru.tsystems.railway.dao.DaoFactory;
import ru.tsystems.railway.dao.StationDao;
import ru.tsystems.railway.dao.TicketDao;
import ru.tsystems.railway.dao.TrainDao;

public class DaoFactoryImpl implements DaoFactory {

    @Override
    public RailwayDaoImpl getRailwayDao() {
        return new RailwayDaoImpl();
    }

    @Override
    public TrainDao getTrainDao() {
        return new TrainDaoImpl();
    }

    @Override
    public StationDao getStationDao() {
        return new StationDaoImpl();
    }

    @Override
    public TicketDao getTicketDao() {
        return new TicketDaoImpl();
    }


}
