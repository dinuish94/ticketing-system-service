package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.dtos.CardDTO;
import lk.sliit.transport.publicTransportService.models.Card;
import lk.sliit.transport.publicTransportService.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jonathan on 11/15/2017.
 */
@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    public CardDTO getCard(String cardRef){
        System.out.println(cardRef);
        CardDTO cardDTO = new CardDTO();
        Card card = cardRepository.findByTokenRef(cardRef);
        if(card != null){
            cardDTO.setCardNo(card.getId());
            cardDTO.setCardRef(card.getTokenRef());
            cardDTO.setBalance(card.getBalance());
        }
        return cardDTO;
    }
}
