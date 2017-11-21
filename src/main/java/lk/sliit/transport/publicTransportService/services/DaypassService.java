package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.dtos.DaypassDTO;
import lk.sliit.transport.publicTransportService.exceptions.InvalidDataException;
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
}
