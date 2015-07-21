package ru.tsystems.railway.dao.impl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.tsystems.railway.dao.EmployeeDao;
import ru.tsystems.railway.domain.management.Employee;
import ru.tsystems.railway.utils.HibernateUtil;

public class EmployeeDaoImpl extends RailwayDaoImpl implements EmployeeDao {

    @Override
    public Employee login(String login, String password) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tr = session.beginTransaction();
        try {
            Query query = session.createQuery("from Employee " +
                    "where login = :login and password = :password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            return (Employee) query.uniqueResult();
        } finally {
            tr.commit();
        }
    }
}
