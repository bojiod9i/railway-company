package ru.tsystems.railway.dao;

import ru.tsystems.railway.domain.Passenger;
import ru.tsystems.railway.domain.Station;
import ru.tsystems.railway.domain.Train;

import java.util.Calendar;
import java.util.Set;

public interface TrainDao extends RailwayDao {
    Set<Train> searchTrain(Station departureStation, Station arrivalStation, Calendar fromDate, Calendar toDate);
    Set<Passenger> getPassengerByTrain(Train train);
}
