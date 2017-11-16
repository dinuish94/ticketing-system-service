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

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles checkins
 * <p>
 * Created by dinukshakandasamanage on 11/13/17.
 */

@Service
public class TripService {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BusRepository busRepository;

    @Autowired
    BusStopRepository busStopRepository;

    public Trip checkin(TripDTO tripDTO) {
        Trip trip = new Trip();
        Card card = cardRepository.findByTokenRef(tripDTO.getTokenRef());

        Bus bus = busRepository.findOne(tripDTO.getBusId());
        BusStop startBusStop = busStopRepository.findByLocation(tripDTO.getStartLocation());
        BusStop endBusStop = busStopRepository.findByLocation(tripDTO.getStartLocation());

        trip.setBus(bus);
        trip.setCard(card);
        trip.setStartBusStop(startBusStop);
        trip.setStartBusStop(endBusStop);

        double price = bus.getBusCategory().getRate() * DistanceCalculationService.getDistance();

        trip.setPrice(price);

        if( price < card.getBalance()){
            card.setBalance(card.getBalance() - price);
            trip.setPayWithCash(0);
            cardRepository.save(card);
        } else {
            trip.setPayWithCash(2);
        }

        return tripRepository.save(trip);
    }

    public void checkout(TripDTO tripDTO) {
        List<Trip> trips = tripRepository.findByCard(cardRepository.findByTokenRef(tripDTO.getTokenRef()));

        // Retrieve incomplete trips
        List<Trip> incompleteTrips = trips.stream().filter(trip -> trip.isCompleted()==false).collect(Collectors.toList());

        // Set most recent incomplete trip as completed
        incompleteTrips.get(incompleteTrips.size()-1).setCompleted(true);
    }
}
