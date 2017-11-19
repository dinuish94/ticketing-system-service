package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.dtos.TripDTO;
import lk.sliit.transport.publicTransportService.exceptions.InvalidDataException;
import lk.sliit.transport.publicTransportService.models.*;
import lk.sliit.transport.publicTransportService.repositories.BusRepository;
import lk.sliit.transport.publicTransportService.repositories.BusStopRepository;
import lk.sliit.transport.publicTransportService.repositories.CardRepository;
import lk.sliit.transport.publicTransportService.repositories.TripRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.ServletContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dinukshakandasamanage on 11/19/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TripServiceTest {

    @Configuration
    static class TripServiceTestContextConfiguration {

        @Bean
        public TripService tripService() {
            return new TripService();
        }

        @Bean
        public TripRepository tripRepository() {
            return Mockito.mock(TripRepository.class);
        }

        @Bean
        public CardRepository cardRepository() {
            return Mockito.mock(CardRepository.class);
        }

        @Bean
        public BusRepository busRepository() {
            return Mockito.mock(BusRepository.class);
        }

        @Bean
        public BusStopRepository busStopRepository() {
            return Mockito.mock(BusStopRepository.class);
        }

        @Bean
        public ServletContext servletContext() {
            return Mockito.mock(ServletContext.class);
        }
    }

    @Autowired
    TripService tripService;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BusRepository busRepository;

    @Autowired
    BusStopRepository busStopRepository;

    @Autowired
    private ServletContext servletContext;

    Trip trip = new Trip();

    @Before
    public void setUp() throws Exception {
        Card card = new Card();
        card.setBalance(200);

        Bus bus = new Bus();

        BusCategory busCategory = new BusCategory();
        busCategory.setType("Luxury");
        busCategory.setRate(20);

        bus.setBusCategory(busCategory);

        BusStop busStop1 = new BusStop();
        busStop1.setLocation("Kolpitty");

        BusStop busStop2 = new BusStop();
        busStop2.setLocation("Bambalapitiya");

        trip.setCard(card);
        trip.setStartBusStop(busStop1);
        trip.setEndBusStop(busStop2);
        trip.setBus(bus);

        Mockito.when(cardRepository.findByTokenRef("abc")).thenReturn(card);
        Mockito.when(busRepository.findOne(1L)).thenReturn(bus);
        Mockito.when(busStopRepository.findByLocation("Kolpitty")).thenReturn(busStop1);
        Mockito.when(busStopRepository.findByLocation("Bambalapitiya")).thenReturn(busStop1);
        Mockito.when(tripRepository.save(Mockito.any(Trip.class))).thenReturn(trip);
        Mockito.when(tripRepository.findOne(1L)).thenReturn(trip);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testTripData() throws Exception {

        TripDTO tripDTO = new TripDTO();
        tripDTO.setTokenRef("abc");
        tripDTO.setBusId(1);
        tripDTO.setStartLocation("Kolpitty");
        tripDTO.setEndLocation("Bambalapitiya");

        Trip trip = tripService.checkin(tripDTO);

        assertEquals("Bambalapitiya", trip.getEndBusStop().getLocation());
        assertEquals("Kolpitty", trip.getStartBusStop().getLocation());

    }

    @Test
    public void testTripFeeCalculation() throws Exception {
        TripDTO tripDTO = new TripDTO();
        tripDTO.setTokenRef("abc");
        tripDTO.setBusId(1);
        tripDTO.setStartLocation("Kolpitty");
        tripDTO.setEndLocation("Bambalapitiya");

        Trip trip = tripService.checkin(tripDTO);
        int distance = trip.getDistance();
        assertEquals(distance * 20, trip.getPrice(), 0.001);
    }

    @Test
    public void testPaymentMethod() throws Exception {
        TripDTO tripDTO = new TripDTO();
        tripDTO.setTokenRef("abc");
        tripDTO.setBusId(1);
        tripDTO.setStartLocation("Kolpitty");
        tripDTO.setEndLocation("Bambalapitiya");

        Trip trip = tripService.checkin(tripDTO);
        double price = trip.getPrice();

        if (price > trip.getCard().getBalance()) {
            assertEquals(trip.getPayWithCash(), 2);
        } else {
            assertEquals(trip.getPayWithCash(), 0);
        }
    }

    @Test(expected = InvalidDataException.class)
    public void testInvalidToken() throws InvalidDataException {
        TripDTO tripDTO = new TripDTO();
        tripDTO.setTokenRef("abcd");
        tripService.checkin(tripDTO);
    }

    @Test
    public void testInvalidTokenMessage() {
        TripDTO tripDTO = new TripDTO();
        tripDTO.setTokenRef("abcd");
        try {
            tripService.checkin(tripDTO);
        } catch (InvalidDataException e) {
            assertEquals("Invalid Token!", e.getMessage().toString());
        }
    }

    @Test(expected = InvalidDataException.class)
    public void testInvalidBusID() throws InvalidDataException {
        TripDTO tripDTO = new TripDTO();
        tripDTO.setBusId(2);
        tripService.checkin(tripDTO);
    }

    @Test
    public void testInvalidBusIDMessage() {
        TripDTO tripDTO = new TripDTO();
        tripDTO.setTokenRef("abc");
        tripDTO.setBusId(2);
        try {
            tripService.checkin(tripDTO);
        } catch (InvalidDataException e) {
            assertEquals("Invalid Bus ID!", e.getMessage().toString());
        }
    }

    @Test(expected = InvalidDataException.class)
    public void testInvalidBusStop() throws InvalidDataException {
        TripDTO tripDTO = new TripDTO();
        tripDTO.setStartLocation("Colombo 7");
        tripService.checkin(tripDTO);
    }

    @Test
    public void testInvalidBusStopMessage() {
        TripDTO tripDTO = new TripDTO();
        tripDTO.setTokenRef("abc");
        tripDTO.setBusId(1);
        tripDTO.setStartLocation("Colombo 7");

        try {
            tripService.checkin(tripDTO);
        } catch (InvalidDataException e) {
            assertEquals("Invalid Bus Stop Location!", e.getMessage().toString());
        }
    }

    @Test
    public void testConfirmCheckin() throws Exception {
        TripDTO tripDTO = new TripDTO();
        tripDTO.setId(1);
        tripDTO.setTokenRef("abc");
        tripDTO.setBusId(1);
        tripDTO.setStartLocation("Kolpitty");
        tripDTO.setEndLocation("Bambalapitiya");

        Trip trip = tripService.confirmCheckin(tripDTO);

        if (trip.getPrice() < 200) {
            assertEquals(200 - trip.getPrice(), trip.getCard().getBalance(), 0.01);
            assertEquals(0, trip.getPayWithCash());
        } else {
            assertEquals(2, trip.getPayWithCash());
        }
    }

    @Test(expected = InvalidDataException.class)
    public void testCheckoutForCardWithoutTrips() throws InvalidDataException {
        Mockito.when(tripRepository.findByCard(Mockito.any())).thenReturn(null);

        TripDTO tripDTO = new TripDTO();
        tripDTO.setId(1);
        tripDTO.setTokenRef("abc");
        tripDTO.setBusId(1);
        tripDTO.setStartLocation("Kolpitty");
        tripDTO.setEndLocation("Bambalapitiya");

        tripService.checkout(tripDTO);
    }

    @Test
    public void testCheckoutForCardWithoutTripsExMessage() {
        Mockito.when(tripRepository.findByCard(Mockito.any())).thenReturn(null);

        TripDTO tripDTO = new TripDTO();
        tripDTO.setId(1);
        tripDTO.setTokenRef("abc");
        tripDTO.setBusId(1);
        tripDTO.setStartLocation("Kolpitty");
        tripDTO.setEndLocation("Bambalapitiya");

        try {
            tripService.checkout(tripDTO);
        } catch (InvalidDataException e) {
            assertEquals("No trips found for this card!", e.getMessage().toString());
        }

    }

    @Test
    public void testCheckout() throws Exception {
        List<Trip> trips = new ArrayList<>();
        trips.add(trip);
        Mockito.when(tripRepository.findByCard(Mockito.any())).thenReturn(trips);

        TripDTO tripDTO = new TripDTO();
        tripDTO.setId(1);
        tripDTO.setTokenRef("abc");
        tripDTO.setBusId(1);
        tripDTO.setStartLocation("Kolpitty");
        tripDTO.setEndLocation("Bambalapitiya");

        Trip returnedTrip = tripService.checkout(tripDTO);
        assertEquals(true, returnedTrip.isCompleted());
    }

    @Test(expected = InvalidDataException.class)
    public void testConfirmPaymentForInvalidTripID() throws Exception {
        tripService.confirmPayment(2);
    }

    @Test
    public void testConfirmPaymentForInvalidTripIDExMessage() {
        try {
            tripService.confirmPayment(2);
        } catch (InvalidDataException e) {
            assertEquals("Failed to find trip: 2 !!!", e.getMessage().toString());
        }
    }

    @Test
    public void testConfirmPayment() throws Exception {
        Trip trip = tripService.confirmPayment(1);
        assertEquals(true, trip.isPaymentDone());
    }

    @Test
    public void testCheckoutForIncompletePayment() throws Exception {

        trip.setPaymentDone(false);
        trip.setPayWithCash(1);

        Mockito.when(tripRepository.save(Mockito.any(Trip.class))).thenReturn(trip);

        List<Trip> trips = new ArrayList<>();
        trips.add(trip);
        Mockito.when(tripRepository.findByCard(Mockito.any())).thenReturn(trips);

        TripDTO tripDTO = new TripDTO();
        tripDTO.setId(1);
        tripDTO.setTokenRef("abc");
        tripDTO.setBusId(1);
        tripDTO.setStartLocation("Kolpitty");
        tripDTO.setEndLocation("Bambalapitiya");

        Trip returnedTrip = tripService.checkout(tripDTO);
        assertEquals(false, returnedTrip.isCompleted());
    }


}