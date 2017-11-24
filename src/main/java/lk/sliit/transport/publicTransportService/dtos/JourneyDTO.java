package lk.sliit.transport.publicTransportService.dtos;

/**
 * Created by dinukshakandasamanage on 11/18/17.
 */
public class JourneyDTO {

    private long id;
    private String startLocation;
    private String endLocation;
    private double price;
    private double rate;
    private String tokenRef;
    private String passenger;
    private int distance;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getTokenRef() {
        return tokenRef;
    }

    public void setTokenRef(String tokenRef) {
        this.tokenRef = tokenRef;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
