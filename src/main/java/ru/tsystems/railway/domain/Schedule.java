package ru.tsystems.railway.domain;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "SCHEDULE")
public class Schedule extends AbstractDomainEntity {

    @Column(name = "DEPARTURE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar departureDate;

    @OneToOne
    @JoinColumn(name = "STATION_ID")
    private Station station;

    @ManyToOne
    @JoinColumn(name = "TRAIN_ID")
    private Train train;

    public Calendar getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Calendar departureDate) {
        this.departureDate = departureDate;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
