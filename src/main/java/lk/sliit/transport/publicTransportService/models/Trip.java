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

    private float price;

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
