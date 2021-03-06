package lk.sliit.transport.publicTransportService.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by dinukshakandasamanage on 11/12/17.
 */
@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "card_id")
    @JsonIgnore
    private Card card;

    @ManyToOne
    @JoinColumn(name = "start_bus_stop_id")
    private BusStop startBusStop;

    @ManyToOne
    @JoinColumn(name = "end_bus_stop_id")
    private BusStop endBusStop;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    private double price;

    private boolean isCompleted = false;

    private int distance;

    private double rate;

    private double currentBalance;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    private Date date;

    private String startTime;

    private String endTime;

    private boolean paymentDone;


    /**
     * Used to track whether the payment is done with cash or card
     * 0 - card
     * 1 - cash
     * 2 - not applicable (this is the intermediate state where the user has not selected an option)
     */
    private int payWithCash;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public BusStop getStartBusStop() {
        return startBusStop;
    }

    public void setStartBusStop(BusStop startBusStop) {
        this.startBusStop = startBusStop;
    }

    public BusStop getEndBusStop() {
        return endBusStop;
    }

    public void setEndBusStop(BusStop endBusstop) {
        this.endBusStop = endBusstop;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getPayWithCash() {
        return payWithCash;
    }

    public void setPayWithCash(int payWithCash) {
        this.payWithCash = payWithCash;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public boolean isPaymentDone() {
        return paymentDone;
    }

    public void setPaymentDone(boolean paymentDone) {
        this.paymentDone = paymentDone;
    }
}
