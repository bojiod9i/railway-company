package ru.tsystems.railway.dao.impl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.tsystems.railway.dao.TicketDao;
import ru.tsystems.railway.domain.Passenger;
import ru.tsystems.railway.domain.Schedule;
import ru.tsystems.railway.domain.Ticket;
import ru.tsystems.railway.domain.Train;
import ru.tsystems.railway.exception.NoAvaliableSeatsException;
import ru.tsystems.railway.exception.OutOfDateException;
import ru.tsystems.railway.exception.PassengerAlreadyRegisteredException;
import ru.tsystems.railway.utils.HibernateUtil;

import java.util.Calendar;
import java.util.HashSet;

public class TicketDaoImpl extends RailwayDaoImpl implements TicketDao {

    @Override
    public void buyTicket(Train train, Passenger passenger)
            throws NoAvaliableSeatsException, OutOfDateException, PassengerAlreadyRegisteredException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tr = session.beginTransaction();
        try {
            Query isAvailableTimeQuery = session.createQuery("from Schedule sch where sch.train = :train order by departureTime desc");
            isAvailableTimeQuery.setParameter("train", train);
            Schedule firstSchedule = (Schedule) isAvailableTimeQuery.list().get(0);
            Calendar availableTime = Calendar.getInstance();
            availableTime.add(Calendar.MINUTE, 10);
            if (firstSchedule.getDepartureDate().compareTo(availableTime) < 0) {
                throw new OutOfDateException("Time of sale is over");
            }

            Query isAlreadySoldQuery = session.createQuery("from Ticket ticket where ticket.passenger = :passenger");
            isAlreadySoldQuery.setParameter("passenger", passenger);
            if (!isAlreadySoldQuery.list().isEmpty()) {
                throw new PassengerAlreadyRegisteredException("Passenger has been registered");
            }
            Query hasFreeTicketQuery = session.createQuery("select count(*) from Ticket ticket where ticket.train = :train");
            Integer numberSoldTicket = (Integer) hasFreeTicketQuery.uniqueResult();
            if (numberSoldTicket >= train.getSeats()) {
                throw new NoAvaliableSeatsException("All tickets has been sold");
            }
            Ticket ticket = new Ticket();
            ticket.setTrain(train);
            ticket.setPassenger(passenger);
            save(ticket);
        } finally {
            tr.commit();
        }

    }

}
