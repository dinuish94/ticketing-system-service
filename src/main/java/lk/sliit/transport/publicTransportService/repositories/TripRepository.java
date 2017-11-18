package lk.sliit.transport.publicTransportService.repositories;

import lk.sliit.transport.publicTransportService.models.BusStop;
import lk.sliit.transport.publicTransportService.models.Card;
import lk.sliit.transport.publicTransportService.models.Trip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by dinukshakandasamanage on 11/13/17.
 */

@Repository
public interface TripRepository extends CrudRepository<Trip, Long> {

    List<Trip> findByCard(Card card);
    List<Trip> findByStartBusStop(BusStop busStop);
    List<Trip> findByEndBusStop(BusStop busStop);
    List<Trip> findByStartBusStopAndEndBusStop(BusStop busStop1,BusStop busStop2);
    List<Trip> findByDate(Date date);
    List<Trip> findByStartTime(String startTime);
    List<Trip> findByEndTime(String endTime);
    List<Trip> findByStartTimeAndEndTime(String startTime, String endTime);
}
