package lk.sliit.transport.publicTransportService.repositories;

import lk.sliit.transport.publicTransportService.models.Card;
import lk.sliit.transport.publicTransportService.models.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dinukshakandasamanage on 11/13/17.
 */

@Repository
public interface TripRepository extends CrudRepository<Trip, Long> {

    List<Trip> findByCard(Card card);
    List<Trip>findByCardByOrderByDateAndOrderByStartTime(Card card);
}
