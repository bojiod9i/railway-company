package ru.tsystems.railway.dao;


import ru.tsystems.railway.domain.Passenger;

import java.util.Date;

public interface PassengerDao extends RailwayDao {
    Passenger getPassengerByParams(String firstName, String secondName, Date birthday);
}
