package ru.tsystems.railway.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.tsystems.railway.dao.StationDao;
import ru.tsystems.railway.domain.Station;
import ru.tsystems.railway.domain.Train;
import ru.tsystems.railway.utils.HibernateUtil;

import java.util.HashSet;
import java.util.Set;

public class StationDaoImpl extends RailwayDaoImpl implements StationDao {

    @Override
    public Set<Train> getArrivingTrains(Station station) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tr = session.beginTransaction();
        try {
            Query query = session.createQuery("select t from Train t join t.schedules sch " +
                    "where sch.station = :depStation");
            query.setParameter("depStation", station);
            return new HashSet<Train>(query.list());
        } finally {
            tr.commit();
        }
    }
}
