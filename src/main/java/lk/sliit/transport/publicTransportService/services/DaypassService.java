package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.dtos.DaypassDTO;

import lk.sliit.transport.publicTransportService.exceptions.InvalidDataException;

import lk.sliit.transport.publicTransportService.exceptions.DaypassNotFound;

import lk.sliit.transport.publicTransportService.models.Card;
import lk.sliit.transport.publicTransportService.models.Daypass;
import lk.sliit.transport.publicTransportService.repositories.CardRepository;
import lk.sliit.transport.publicTransportService.repositories.DaypassRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Jonathan on 11/15/2017.
 */
@Service
public class DaypassService {

    @Autowired
    DaypassRepository daypassRepository;

    @Autowired
    CardRepository cardRepository;

    Logger logger = LoggerFactory.getLogger(DaypassService.class);

    /**
     * Adds a daypass for a passenger card
     *
     * @param daypassDTO for the daypass
     * @return
     */
    public ResponseEntity<String> addDayPass(DaypassDTO daypassDTO) throws InvalidDataException{
        Daypass daypass = new Daypass();

        logger.info("Retrieving card by token " + daypassDTO.getCardRef() + " ...");

        Card card = cardRepository.findByTokenRef(daypassDTO.getCardRef());

        if (null == card) {
            logger.error("Invalid Token!");
            throw new InvalidDataException("Invalid Token!");
        }

        daypass.setDate(daypassDTO.getPassDate());
        daypass.setCard(card);
        card.getDaypasses().add(daypassRepository.save(daypass));
        cardRepository.save(card);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public boolean getDayPassForCard(String token){
        Card card = cardRepository.findByTokenRef(token);

        if (null != card.getDaypasses()){

            for (Daypass daypass : card.getDaypasses()){
                Date daypassDate = daypass.getDate();
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(new Date());
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(daypassDate);

                return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                        cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                        cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
            }
        }
        return false;
    }

    public List<DaypassDTO> getDaypassesForPassenger(String token){
        List<DaypassDTO> daypassDTOS = new ArrayList<>();
        Card card = cardRepository.findByTokenRef(token);
        if (null != card.getDaypasses()){
            card.getDaypasses().forEach(daypass->{
                DaypassDTO daypassDTO = new DaypassDTO();
                daypassDTO.setCardRef(daypass.getCard().getTokenRef());
                daypassDTO.setPassDate(daypass.getDate());
                daypassDTOS.add(daypassDTO);
            });
        }

        return daypassDTOS;
    }
}
