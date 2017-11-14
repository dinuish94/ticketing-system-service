package lk.sliit.transport.publicTransportService.models;

import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by dinukshakandasamanage on 11/12/17.
 */
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String tokenRef;
    private Date expDate;

    @OneToOne(mappedBy = "card", cascade = CascadeType.ALL, optional = false)
    private Account account;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<TopUp> topUps;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Trip> trips;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Daypass> daypasses;

    private double balance;

    public Card() {
        tokenRef = RandomStringUtils.random(8, true, true);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTokenRef() {
        return tokenRef;
    }

    public void setTokenRef(String tokenRef) {
        this.tokenRef = tokenRef;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<TopUp> getTopUps() {
        return topUps;
    }

    public void setTopUps(List<TopUp> topUps) {
        this.topUps = topUps;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Daypass> getDaypasses() {
        return daypasses;
    }

    public void setDaypasses(List<Daypass> daypasses) {
        this.daypasses = daypasses;
    }
}
