package lk.sliit.transport.publicTransportService.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dinukshakandasamanage on 11/12/17.
 */
@Entity
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<Trip> trips;

    @OneToOne(mappedBy = "bus", cascade = CascadeType.ALL, optional = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "bus_category")
    @JsonIgnore
    private BusCategory busCategory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public BusCategory getBusCategory() {
        return busCategory;
    }

    public void setBusCategory(BusCategory busCategory) {
        this.busCategory = busCategory;
    }
}
