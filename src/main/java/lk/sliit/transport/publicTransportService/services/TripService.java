package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.dtos.JourneyDTO;
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

import java.util.ArrayList;
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

    /**
     * Creates a trip
     *
     * @param tripDTO
     * @return
     */
    public Trip checkin(TripDTO tripDTO) {
        Trip trip = new Trip();
        Card card = cardRepository.findByTokenRef(tripDTO.getTokenRef());

        Bus bus = busRepository.findOne(tripDTO.getBusId());
        BusStop startBusStop = busStopRepository.findByLocation(tripDTO.getStartLocation());
        BusStop endBusStop = busStopRepository.findByLocation(tripDTO.getEndLocation());

        trip.setBus(bus);
        trip.setCard(card);
        trip.setStartBusStop(startBusStop);
        trip.setEndBusStop(endBusStop);

        int distance = DistanceCalculationService.getDistance();
        double rate = bus.getBusCategory().getRate();
        double price = rate * distance;

        trip.setRate(rate);
        trip.setDistance(distance);

        trip.setPrice(price);
        trip.setCurrentBalance(card.getBalance());

        // If the card has sufficient balance to complete the journey
        if (price < card.getBalance()) {
            trip.setPayWithCash(0);
        } else {
            // User will have to confirm later if they want to pay with cash or not
            // till then we assign an intermediary state as 2
            trip.setPayWithCash(2);
        }

        return tripRepository.save(trip);
    }

    /**
     * Retrieves the most recent incomplete trip for a particular card and marks it as complete
     *
     * @param tripDTO
     * @return
     */
    public Trip checkout(TripDTO tripDTO) {
        List<Trip> trips = tripRepository.findByCard(cardRepository.findByTokenRef(tripDTO.getTokenRef()));

        // Retrieve incomplete trips
        List<Trip> incompleteTrips = trips.stream().filter(trip -> trip.isCompleted() == false).collect(Collectors.toList());

        // Set most recent incomplete trip as completed
        Trip trip = incompleteTrips.get(incompleteTrips.size() - 1);
        trip.setCompleted(true);
        return tripRepository.save(trip);
    }

    public Trip confirmCheckin(TripDTO tripDTO) {
        Trip trip = tripRepository.findOne(tripDTO.getId());
        Card card = cardRepository.findByTokenRef(tripDTO.getTokenRef());

        // If the payment is to be made with Cash reduce it from the card balance
        if (tripDTO.getPayWithCash() == 0) {
            card.setBalance(card.getBalance() - trip.getPrice());
            cardRepository.save(card);
        }

        trip.setPayWithCash(tripDTO.getPayWithCash());
        return tripRepository.save(trip);
    }

    /**
     * Retrieves a list of trips that are not yet completed for a particular bus
     * for which payments has to be done in cash
     *
     * @param busId
     * @return
     */
    public List<JourneyDTO> getTripsForBus(long busId) {
        List<JourneyDTO> journeys = new ArrayList<>();
        tripRepository.findAll().forEach(trip -> {
            if (trip.getBus().getId() == busId && !trip.isCompleted() && trip.getPayWithCash()==1 ) {
                JourneyDTO journey = new JourneyDTO();
                journey.setId(trip.getId());
                journey.setRate(trip.getRate());
                journey.setPrice(trip.getPrice());
                // If the passenger is a visitor
                if (trip.getCard().getVisitor() != null){
                    journey.setPassenger(trip.getCard().getVisitor().getName());
                } else if (trip.getCard().getAccount().getDailyPassenger() != null){
                    journey.setPassenger(trip.getCard().getAccount().getDailyPassenger().getName());
                }
                journey.setStartBusStop(trip.getStartBusStop().getLocation());
                journey.setEndBusStop(trip.getEndBusStop().getLocation());
                journey.setTokenRef(trip.getCard().getTokenRef());
                journey.setDistance(trip.getDistance());
                journeys.add(journey);
            }
        });
        return journeys;
    }

    /**
     * Retrieves the list of journeys that a particular user has taken
     *
     * @param token token of the card belonging to a certain passenger
     * @return the list of trips
     */
    public List<Trip> getTripsForPassenger(String token){
        List<Trip> trips = new ArrayList<>();

        Card card = cardRepository.findByTokenRef(token);
        if (card != null){
            card.getTrips().forEach(trip -> trips.add(trip));
        }
        return trips;
    }
}
