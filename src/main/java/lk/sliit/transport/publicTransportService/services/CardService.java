package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.dtos.CardDTO;
import lk.sliit.transport.publicTransportService.models.Card;
import lk.sliit.transport.publicTransportService.exceptions.CardNotFoundException;
import lk.sliit.transport.publicTransportService.exceptions.InvalidAccountException;
import lk.sliit.transport.publicTransportService.models.Passenger;
import lk.sliit.transport.publicTransportService.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dinukshakandasamanage on 11/15/17.
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
  
    public boolean validateCard(String token){
        if( null == cardRepository.findByTokenRef(token)){
            return false;
        }
        return true;
    }

    public Passenger findPassengerByToken(String token) throws CardNotFoundException, InvalidAccountException {
        Card card = cardRepository.findByTokenRef(token);
        Passenger passenger;

        if (null == card){
            throw new CardNotFoundException();
        }

        if (null != card.getAccount()){
            passenger = card.getAccount().getDailyPassenger();
        } else {
            passenger = card.getVisitor();
        }

        if (null != passenger){
            return passenger;
        }
        throw new InvalidAccountException();
    }
}
