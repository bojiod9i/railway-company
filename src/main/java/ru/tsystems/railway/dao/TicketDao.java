package ru.tsystems.railway.dao;


import ru.tsystems.railway.domain.Passenger;
import ru.tsystems.railway.domain.Train;
import ru.tsystems.railway.exception.NoAvaliableSeatsException;
import ru.tsystems.railway.exception.OutOfDateException;
import ru.tsystems.railway.exception.PassengerAlreadyRegisteredException;

public interface TicketDao extends RailwayDao {

    void buyTicket(Train train, Passenger passenger)
            throws NoAvaliableSeatsException, OutOfDateException, PassengerAlreadyRegisteredException;

}
