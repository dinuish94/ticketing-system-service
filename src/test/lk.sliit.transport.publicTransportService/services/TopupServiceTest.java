package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.dtos.TopupDTO;
import lk.sliit.transport.publicTransportService.dtos.TripDTO;
import lk.sliit.transport.publicTransportService.exceptions.InvalidDataException;
import lk.sliit.transport.publicTransportService.models.*;
import lk.sliit.transport.publicTransportService.repositories.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.ServletContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Jonathan on 11/22/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TopupServiceTest {

    @Configuration
    static class TripServiceTestContextConfiguration {
        @Bean
        public TopupService topupService() {
            return new TopupService();
        }

        @Bean
        public TopupRepository topupRepository() {
            return Mockito.mock(TopupRepository.class);
        }

        @Bean
        public CardRepository cardRepository() {
            return Mockito.mock(CardRepository.class);
        }

        @Bean
        public ServletContext servletContext() {
            return Mockito.mock(ServletContext.class);
        }

    }

    @Autowired
    TopupService topupService;

    @Autowired
    TopupRepository topupRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    private ServletContext servletContext;


    @Before
    public void setUp() throws Exception {

        Card card = new Card();
        card.setBalance(200.0);

        TopUp topUp = new TopUp();
        topUp.setCard(card);
        topUp.setAmount(120.0);
        card.setBalance(card.getBalance()+topUp.getAmount());
//        card.getTopUps().add(topUp);

        Mockito.when(cardRepository.findByTokenRef("abc")).thenReturn(card);
        Mockito.when(cardRepository.save(Mockito.any(Card.class))).thenReturn(card);


    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addTopup() throws Exception {
        TopUp topUp = new TopUp();

        Card card = cardRepository.findByTokenRef("abc");

        topUp.setCard(card);
        topUp.setAmount(50.0);
        card.setBalance(card.getBalance()+topUp.getAmount());
        assertEquals(370.0,card.getBalance(),0.0);
    }

    @Test
    public void getTopUpsForPassenger() throws Exception {

        List<TopUp> topUps = new ArrayList<>();
        Card card = cardRepository.findByTokenRef("abc");
        if (card != null) {
            if (card.getTopUps() != null) {
                card.getTopUps().forEach(topup -> topUps.add(topup));

            }
        }
        assertEquals(0,topUps.size());

    }

}