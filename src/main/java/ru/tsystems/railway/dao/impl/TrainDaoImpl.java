package ru.tsystems.railway.dao.impl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.tsystems.railway.dao.TrainDao;
import ru.tsystems.railway.domain.Passenger;
import ru.tsystems.railway.domain.Station;
import ru.tsystems.railway.domain.Train;
import ru.tsystems.railway.utils.HibernateUtil;

import java.util.*;

public class TrainDaoImpl extends RailwayDaoImpl implements TrainDao {

    @Override
    public Set<Train> searchTrain(Station departureStation, Station arrivalStation, Calendar fromDate, Calendar toDate) {
        if (fromDate.compareTo(toDate) < 0) {
            return Collections.emptySet();
        }
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tr = session.beginTransaction();
        try {
            Query query = session.createQuery("select t from Train t join t.schedules sch1 join t.schedules sch2 " +
                    "where sch1.station = :depStation AND sch2.station = :arrStation AND sch1.departureDate < sch2.departureDate " +
                    "AND sch1.departureDate > :fromDate AND sch1.departureDate < :toDate");
            query.setParameter("depStation", departureStation);
            query.setParameter("arrStation", arrivalStation);
            query.setParameter("fromDate", fromDate);
            query.setParameter("toDate", toDate);
            return new HashSet(query.list());
        } finally {
            tr.commit();
        }
    }

    @Override
    public Set<Passenger> getPassengerByTrain(Train train) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tr = session.beginTransaction();
        try {
            Query query = session.createQuery("select p from Ticket ticket join Passenger p where ticket.train = :train");
            query.setParameter("train", train);
            return new HashSet(query.list());
        } finally {
            tr.commit();
        }
    }

}
