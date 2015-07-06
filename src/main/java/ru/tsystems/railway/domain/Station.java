package ru.tsystems.railway.domain;

import javax.persistence.*;

@Entity
@Table(name = "STATION")
public class Station extends AbstractDomainEntity {

    @Column(name = "NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
