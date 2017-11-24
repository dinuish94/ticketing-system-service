package lk.sliit.transport.publicTransportService.dtos;

import lk.sliit.transport.publicTransportService.models.BusStop;

/**
 * Created by kashifroshen on 11/19/17.
 */
public class BusStopCountDTO {
    Long count;
    BusStop busStop;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BusStop getBusStop() {
        return busStop;
    }

    public void setBusStop(BusStop busStop) {
        this.busStop = busStop;
    }
}
