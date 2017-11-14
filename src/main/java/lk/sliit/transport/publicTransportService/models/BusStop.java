package lk.sliit.transport.publicTransportService.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dinukshakandasamanage on 11/12/17.
 */

@Entity
public class BusStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String location;

    @JsonIgnore
    @OneToMany(mappedBy = "startBusStop", cascade = CascadeType.ALL)
    private List<Trip> startTrips;

    @JsonIgnore
    @OneToMany(mappedBy = "endBusStop", cascade = CascadeType.ALL)
    private List<Trip> endTrips;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Trip> getStartTrips() {
        return startTrips;
    }

    public void setStartTrips(List<Trip> startTrips) {
        this.startTrips = startTrips;
    }

    public List<Trip> getEndTrips() {
        return endTrips;
    }

    public void setEndTrips(List<Trip> endTrips) {
        this.endTrips = endTrips;
    }
}
