package lk.sliit.transport.publicTransportService.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.sliit.transport.publicTransportService.dtos.TripDTO;
import lk.sliit.transport.publicTransportService.models.*;
import lk.sliit.transport.publicTransportService.repositories.BusRepository;
import lk.sliit.transport.publicTransportService.repositories.BusStopRepository;
import lk.sliit.transport.publicTransportService.repositories.CardRepository;
import lk.sliit.transport.publicTransportService.repositories.TripRepository;
import lk.sliit.transport.publicTransportService.services.CardService;
import lk.sliit.transport.publicTransportService.services.TripService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.ServletContext;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dinukshakandasamanage on 11/19/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CheckinController.class)
public class CheckinControllerTest {


    @Autowired
    private CheckinController checkinController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripService service;

    @MockBean
    private CardService cardService;

    @Configuration
    static class TripServiceTestContextConfiguration {

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

        @Bean
        public CheckinController checkinController(){
            return new CheckinController();
        }
    }

    @Autowired
    TripRepository tripRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BusRepository busRepository;

    @Autowired
    BusStopRepository busStopRepository;

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
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void checkin() throws Exception {

    }

    @Test
    public void checkinShouldReturnOK() throws Exception {
        TripDTO tripDTO = new TripDTO();
        tripDTO.setTokenRef("abc");
        tripDTO.setBusId(1);
        tripDTO.setStartLocation("Kolpitty");
        tripDTO.setEndLocation("Bambalapitiya");

        ObjectMapper mapper = new ObjectMapper();
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/checkin/");
        mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON);
        mockHttpServletRequestBuilder.content(mapper.writeValueAsString(tripDTO).getBytes());

        when(service.checkin(tripDTO)).thenReturn(trip);
        this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk());

    }

    @Test
    public void validateValidTokenShouldReturnOK() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/checkin?token=abc");
        mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON);

        when(cardService.validateCard("abc")).thenReturn(true);
        this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void validateInvalidTokenShouldReturnOK() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/checkin?token=abc");
        mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON);

        when(cardService.validateCard("abc")).thenReturn(false);
        this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void findPassengerByTokenShouldReturnOK() throws Exception {
        Passenger passenger = new Visitor();
        when(cardService.findPassengerByToken("abc")).thenReturn(passenger);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/checkin/passenger?token=abc");
        mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk());

    }

    @Test
    public void findPassengerByTokenShouldReturnBadRequest() throws Exception {
        Passenger passenger = new Visitor();
        when(cardService.findPassengerByToken("abc")).thenReturn(passenger);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/checkin/passenger");
        mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isBadRequest());

    }

    @Test
    public void confirmCheckinShouldReturnOK() throws Exception {
        TripDTO tripDTO = new TripDTO();
        tripDTO.setId(1);
        tripDTO.setTokenRef("abc");
        tripDTO.setBusId(1);
        tripDTO.setStartLocation("Kolpitty");
        tripDTO.setEndLocation("Bambalapitiya");

        ObjectMapper mapper = new ObjectMapper();
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.put("/checkin/");
        mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON);
        mockHttpServletRequestBuilder.content(mapper.writeValueAsString(tripDTO).getBytes());

        when(service.confirmCheckin(tripDTO)).thenReturn(trip);
        this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk());
    }

}