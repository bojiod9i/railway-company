package ru.tsystems.railway.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.tsystems.railway.dao.StationDao;
import ru.tsystems.railway.domain.Station;
import ru.tsystems.railway.domain.Train;
import ru.tsystems.railway.utils.HibernateUtil;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class StationDaoImpl extends RailwayDaoImpl implements StationDao {

    @Override
    public Set<Train> getTrainsByDate(Station station, Calendar fromDate, Calendar toDate) {
        if (fromDate.compareTo(toDate) > 0) {
            return Collections.emptySet();
        }
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tr = session.beginTransaction();
        try {
            Query query = session.createQuery("select t from Train t join t.schedules sch " +
                    "where sch.station = :depStation " +
                    "and sch.departureDate > :fromDate and sch.departureDate < :toDate ");
            query.setParameter("depStation", station);
            query.setParameter("fromDate", fromDate);
            query.setParameter("toDate", toDate);
            return new HashSet<Train>(query.list());
        } finally {
            tr.commit();
        }
    }

    @Override
    public Station getStationByName(String name) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tr = session.beginTransaction();
        try {
            Query query = session.createQuery("from Station " +
                    "where name = :name");
            query.setParameter("name", name);
            return (Station) query.uniqueResult();
        } finally {
            tr.commit();
        }
    }
}
