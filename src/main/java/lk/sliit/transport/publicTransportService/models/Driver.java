package lk.sliit.transport.publicTransportService.models;

import javax.persistence.*;

/**
 * Created by dinukshakandasamanage on 11/12/17.
 */

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "id")
    private Bus bus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
}
