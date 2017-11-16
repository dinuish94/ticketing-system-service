package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.dtos.DaypassDTO;
import lk.sliit.transport.publicTransportService.models.Card;
import lk.sliit.transport.publicTransportService.models.Daypass;
import lk.sliit.transport.publicTransportService.repositories.CardRepository;
import lk.sliit.transport.publicTransportService.repositories.DaypassRepository;
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

    public ResponseEntity<String> addDayPass(DaypassDTO daypassDTO){
        Daypass daypass = new Daypass();
        Card card = cardRepository.findByTokenRef(daypassDTO.getCardRef());
        daypass.setDate(daypassDTO.getPassDate());
        daypass.setCard(card);
        card.getDaypasses().add(daypassRepository.save(daypass));
        cardRepository.save(card);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
