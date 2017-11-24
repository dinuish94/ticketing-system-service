package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.dtos.JourneyDTO;
import lk.sliit.transport.publicTransportService.dtos.TripDTO;
import lk.sliit.transport.publicTransportService.exceptions.InvalidDataException;
import lk.sliit.transport.publicTransportService.models.*;
import lk.sliit.transport.publicTransportService.repositories.BusRepository;
import lk.sliit.transport.publicTransportService.repositories.BusStopRepository;
import lk.sliit.transport.publicTransportService.repositories.CardRepository;
import lk.sliit.transport.publicTransportService.repositories.TripRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    @Autowired
    DaypassService daypassService;

    Logger logger = LoggerFactory.getLogger(TripService.class);

    /**
     * Creates a trip
     *
     * @param tripDTO
     * @return
     */
    public Trip checkin(TripDTO tripDTO) throws InvalidDataException {
        Trip trip = new Trip();
        logger.info("Retrieving card by token " + tripDTO.getTokenRef() + " ...");
        Card card = cardRepository.findByTokenRef(tripDTO.getTokenRef());

        if (null == card) {
            logger.error("Invalid Token!");
            throw new InvalidDataException("Invalid Token!");
        }

        Bus bus = busRepository.findOne(tripDTO.getBusId());

        if (null == bus) {
            logger.error("Invalid Bus ID!");
            throw new InvalidDataException("Invalid Bus ID!");
        }
        BusStop startBusStop = busStopRepository.findByLocation(tripDTO.getStartLocation());
        BusStop endBusStop = busStopRepository.findByLocation(tripDTO.getEndLocation());

        if (null == startBusStop || null == endBusStop) {
            logger.error("Invalid Bus Stop Location!");
            throw new InvalidDataException("Invalid Bus Stop Location!");
        }

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
        boolean hasDayPass = hasDayPass(card);

        // If the card has sufficient balance to complete the journey
        if (hasDayPass || price <= card.getBalance()) {
            logger.info("Passenger has sufficient balance in card ...");
            trip.setPayWithCash(0);
        } else {
            // User will have to confirm later if they want to pay with cash or not
            // till then we assign an intermediary state as 2
            logger.info("Passenger has insufficient balance in card ...");
            logger.info("Setting payment option to cash ...");
            trip.setPayWithCash(2);
        }

        return tripRepository.save(trip);
    }

    /**
     * Checks whether a particular card has a day pass for today
     *
     * @param card
     * @return true if a day pass is available
     */
    private Boolean hasDayPass(Card card) {
        return daypassService.getDayPassForCard(card.getTokenRef());
    }

    /**
     * Confirms the trip
     * Fee is reduced from the balance if sufficient amount is remaining in the card
     * Else record is marked as to be paid with cash
     *
     * @param tripDTO
     * @return
     */
    public Trip confirmCheckin(TripDTO tripDTO) {
        Trip trip = tripRepository.findOne(tripDTO.getId());
        Card card = cardRepository.findByTokenRef(trip.getCard().getTokenRef());

        boolean hasDayPass = hasDayPass(card);

        // If the payment is to be made with Card and has no day pass reduce it from the card balance
        if (tripDTO.getPayWithCash() == 0 && !hasDayPass) {
            logger.info("Reducing fee from balance in card ...");
            card.setBalance(card.getBalance() - trip.getPrice());
            logger.info("Payment is complete ...");
            trip.setPaymentDone(true);
            cardRepository.save(card);
        } else if (hasDayPass) {
            // If a day pass is available mark it as payment done
            trip.setPaymentDone(true);
        }

        trip.setPayWithCash(tripDTO.getPayWithCash());
        return tripRepository.save(trip);
    }

    /**
     * Retrieves the most recent incomplete trip for a particular card and marks it as complete
     *
     * @param tripDTO
     * @return
     */
    public Trip checkout(TripDTO tripDTO) throws InvalidDataException {
        List<Trip> trips = tripRepository.findByCard(cardRepository.findByTokenRef(tripDTO.getTokenRef()));

        if (null == trips) {
            logger.error("No trips found for this card!");
            throw new InvalidDataException("No trips found for this card!");
        }

        // Retrieve incomplete trips
        List<Trip> incompleteTrips = trips.stream().filter(trip -> !trip.isCompleted()).collect(Collectors.toList());

        logger.info("Retrieving most recent incomplete trip ...");
        // Set most recent incomplete trip as completed
        Trip trip = incompleteTrips.get(incompleteTrips.size() - 1);

        // Check if the payment method is cash, whether the payment is confirmed by the driver.
        if (!trip.isPaymentDone() && trip.getPayWithCash() == 1) {
            logger.error("Payment is not done for the trip! Checkout unsuccessful!");
            return trip;
        }
        logger.info("Checking out ...");
        trip.setCompleted(true);
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

        logger.info("Retrieving trips for" + busId + "...");
        tripRepository.findAll().forEach(trip -> {
            if (trip.getBus().getId() == busId && !trip.isCompleted() && trip.getPayWithCash() == 1 && !trip.isPaymentDone()) {
                JourneyDTO journey = new JourneyDTO();
                journey.setId(trip.getId());
                journey.setRate(trip.getRate());
                journey.setPrice(trip.getPrice());

                // If the passenger is a visitor
                if (trip.getCard().getVisitor() != null) {
                    journey.setPassenger(trip.getCard().getVisitor().getName());
                } else if (trip.getCard().getAccount().getDailyPassenger() != null) {
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
     * Confirms a payment done by cash
     *
     * @param tripId
     * @return
     */
    public Trip confirmPayment(long tripId) throws InvalidDataException {
        Trip trip = tripRepository.findOne(tripId);
        if (trip != null) {
            logger.info("Confirming payment for" + tripId + "...");
            trip.setPaymentDone(true);
            return tripRepository.save(trip);
        }
        logger.error("Failed to find trip: " + tripId + " !!!");
        throw new InvalidDataException("Failed to find trip: " + tripId + " !!!");
    }

    public List<Trip> getTripsByCard(String tokenRef) {
        Card card = cardRepository.findByTokenRef(tokenRef);

        return tripRepository.findByCardByOrderByDateAndOrderByStartTime(card);
    }
}
