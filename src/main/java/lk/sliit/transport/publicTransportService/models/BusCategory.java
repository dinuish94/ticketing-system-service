package lk.sliit.transport.publicTransportService.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dinukshakandasamanage on 11/12/17.
 */
@Entity
public class BusCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "busCategory", cascade = CascadeType.ALL)
    private List<Bus> buses;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }
}
