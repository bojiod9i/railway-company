package ru.tsystems.railway.dao;

import ru.tsystems.railway.domain.Station;
import ru.tsystems.railway.domain.Train;

import java.util.Calendar;
import java.util.Set;

public interface StationDao extends RailwayDao {

    Set<Train> getTrainsByDate(Station station, Calendar fromDate, Calendar toDate);

    Station getStationByName(String name);
}
