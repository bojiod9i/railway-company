package ru.tsystems.railway.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "TRAIN")
public class Train extends AbstractDomainEntity {

    @OneToMany(mappedBy = "train", fetch = FetchType.EAGER)
    @OrderBy("departureDate ASC")
    private Set<Schedule> schedules;

    @Column(name = "SEATS")
    private Integer seats;

    public Train() {
    }

    public Train(Integer seats) {
        this.seats = seats;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}
