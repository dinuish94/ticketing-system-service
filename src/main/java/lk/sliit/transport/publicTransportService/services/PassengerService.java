package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.models.Card;
import lk.sliit.transport.publicTransportService.models.Passenger;
import lk.sliit.transport.publicTransportService.models.Trip;
import lk.sliit.transport.publicTransportService.repositories.CardRepository;
import lk.sliit.transport.publicTransportService.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 11/15/2017.
 */
@Service
public class PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    CardRepository cardRepository;

    /**
     * Retrieves all passengers
     *
     * @return list of passengers
     */
    public List<Passenger> getAllPassengers(){
        List<Passenger> passengers = new ArrayList<>();
        passengerRepository.findAll().forEach(passenger->{
            passengers.add(passenger);
        });
        return passengers;
    }


    /**
     * Retrieves the list of journeys that a particular user has taken
     *
     * @param token token of the card belonging to a certain passenger
     * @return the list of trips
     */
    public List<Trip> getTripsForPassenger(String token) {
        List<Trip> trips = new ArrayList<>();

        Card card = cardRepository.findByTokenRef(token);
        if (card != null) {
            card.getTrips().forEach(trips::add);
        }
        return trips;
    }

}
