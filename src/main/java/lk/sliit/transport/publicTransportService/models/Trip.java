package lk.sliit.transport.publicTransportService.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

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
}
