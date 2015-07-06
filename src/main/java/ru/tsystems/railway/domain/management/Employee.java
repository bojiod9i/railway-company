package ru.tsystems.railway.domain.management;

import ru.tsystems.railway.domain.AbstractDomainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee extends AbstractDomainEntity {

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private Byte[] password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Byte[] getPassword() {
        return password;
    }

    public void setPassword(Byte[] password) {
        this.password = password;
    }
}
