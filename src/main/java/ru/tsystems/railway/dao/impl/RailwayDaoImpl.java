package ru.tsystems.railway.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.tsystems.railway.dao.RailwayDao;
import ru.tsystems.railway.domain.AbstractDomainEntity;
import ru.tsystems.railway.utils.HibernateUtil;

import java.util.HashSet;
import java.util.Set;

public class RailwayDaoImpl implements RailwayDao {

    @Override
    public <T extends AbstractDomainEntity> T getEntityById(Class<T> clazz, Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tr = session.beginTransaction();
        try {
            return (T) session.get(clazz, id);
        } finally {
            tr.commit();
        }
    }

    @Override
    public <T extends AbstractDomainEntity> Set<T> list(Class<T> clazz) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tr = session.beginTransaction();
        try {
            return new HashSet<T>(session.createCriteria(clazz).list());
        } finally {
            tr.commit();
        }
    }

    @Override
    public <T extends AbstractDomainEntity> void save(T entity) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tr = session.beginTransaction();
        try {
            session.save(entity);
        } finally {
            tr.commit();
        }
    }
}
