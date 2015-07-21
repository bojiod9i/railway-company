package ru.tsystems.railway.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.tsystems.railway.dao.PassengerDao;
import ru.tsystems.railway.domain.Passenger;
import ru.tsystems.railway.utils.HibernateUtil;

import java.util.Date;

public class PassengerDaoImpl extends RailwayDaoImpl implements PassengerDao {

    @Override
    public Passenger getPassengerByParams(String firstName, String lastName, Date birthday) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tr = session.beginTransaction();
        try {
            Query query = session.createQuery("from Passenger " +
                    "where firstName = :firstName and lastName = :lastName " +
                    "and birthday = :birthday");
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            query.setParameter("birthday", birthday);
            return (Passenger) query.uniqueResult();
        } finally {
            tr.commit();
        }
    }

}
