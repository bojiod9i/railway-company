package ru.tsystems.railway.dao;

import ru.tsystems.railway.domain.AbstractDomainEntity;

import java.util.Set;

public interface RailwayDao {
    <T extends AbstractDomainEntity> T getEntityById(Class<T> clazz, Long id);

    <T extends AbstractDomainEntity> Set<T> list(Class<T> clazz);

    <T extends AbstractDomainEntity> void save(T entity);
}
