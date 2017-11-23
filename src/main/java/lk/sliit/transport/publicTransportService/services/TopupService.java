package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.dtos.DaypassDTO;
import lk.sliit.transport.publicTransportService.dtos.TopupDTO;
import lk.sliit.transport.publicTransportService.exceptions.InvalidDataException;
import lk.sliit.transport.publicTransportService.models.Card;
import lk.sliit.transport.publicTransportService.models.TopUp;
import lk.sliit.transport.publicTransportService.repositories.CardRepository;
import lk.sliit.transport.publicTransportService.repositories.TopupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 11/15/2017.
 */

@Service
public class TopupService {

    @Autowired
    TopupRepository topupRepository;

    @Autowired
    CardRepository cardRepository;

    Logger logger = LoggerFactory.getLogger(TopupService.class);

    /**
     * Adds a topup for a passenger card
     *
     * @param topupDTO for the topup
     * @return
     */

    public ResponseEntity<String> addTopup(TopupDTO topupDTO) throws InvalidDataException{
        TopUp topUp = new TopUp();

        logger.info("Retrieving card by token " + topupDTO.getCardRef() + " ...");

        Card card = cardRepository.findByTokenRef(topupDTO.getCardRef());

        if (null == card) {
            logger.error("Invalid Token!");
            throw new InvalidDataException("Invalid Token!");
        }

        topUp.setCard(card);
        topUp.setAmount(topupDTO.getAmount());
        card.setBalance(card.getBalance()+topupDTO.getAmount());
        card.getTopUps().add(topupRepository.save(topUp));
        cardRepository.save(card);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Retrieves top up history for a particular passenger
     *
     * @param token for the passenger card
     * @return
     */
    public List<TopUp> getTopUpsForPassenger(String token){
        List<TopUp> topUps = new ArrayList<>();

        Card card = cardRepository.findByTokenRef(token);
        if (card != null){
            card.getTopUps().forEach(trip -> topUps.add(trip));
        }
        return topUps;
    }


}
