package ru.tsystems.railway.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "STATION")
public class Station extends AbstractDomainEntity {

    @Column(name = "NAME")
    private String name;

    public Station() {
    }

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
