package ru.tsystems.railway.dao;


import ru.tsystems.railway.domain.management.Employee;

public interface EmployeeDao extends RailwayDao {

    Employee login(String username, String password);
}
