package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.dtos.TripDTO;
import lk.sliit.transport.publicTransportService.models.Bus;
import lk.sliit.transport.publicTransportService.models.BusStop;
import lk.sliit.transport.publicTransportService.models.Card;
import lk.sliit.transport.publicTransportService.models.Trip;
import lk.sliit.transport.publicTransportService.repositories.BusRepository;
import lk.sliit.transport.publicTransportService.repositories.BusStopRepository;
import lk.sliit.transport.publicTransportService.repositories.CardRepository;
import lk.sliit.transport.publicTransportService.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handles checkins
 *
 * Created by dinukshakandasamanage on 11/13/17.
 */

@Service
public class CheckinService {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BusRepository busRepository;

    @Autowired
    BusStopRepository busStopRepository;

    public Trip checkin(TripDTO tripDTO){
        Trip trip = new Trip();
        Card card = cardRepository.findByTokenRef(tripDTO.getTokenRef());
        busRepository.findAll().forEach(bus -> {
            if (tripDTO.getBusId() == bus.getId()){
                trip.setBus(bus);
            }
        });
        BusStop busStop = busStopRepository.findByLocation(tripDTO.getStartLocation());

        trip.setCard(card);
        trip.setStartBusStop(busStop);

        return tripRepository.save(trip);
    }
}
