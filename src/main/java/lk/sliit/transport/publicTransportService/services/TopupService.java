package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.dtos.DaypassDTO;
import lk.sliit.transport.publicTransportService.dtos.TopupDTO;
import lk.sliit.transport.publicTransportService.models.Card;
import lk.sliit.transport.publicTransportService.models.TopUp;
import lk.sliit.transport.publicTransportService.repositories.CardRepository;
import lk.sliit.transport.publicTransportService.repositories.TopupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by Jonathan on 11/15/2017.
 */

@Service
public class TopupService {

    @Autowired
    TopupRepository topupRepository;

    @Autowired
    CardRepository cardRepository;

    public ResponseEntity<String> addTopup(TopupDTO topupDTO){
        TopUp topUp = new TopUp();
        Card card = cardRepository.findByTokenRef(topupDTO.getCardRef());
        topUp.setCard(card);
        topUp.setAmount(topupDTO.getAmount());
        card.setBalance(card.getBalance()+topupDTO.getAmount());
        card.getTopUps().add(topupRepository.save(topUp));
        cardRepository.save(card);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
