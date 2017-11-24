package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.dtos.CardDTO;
import lk.sliit.transport.publicTransportService.dtos.PassengerDTO;
import lk.sliit.transport.publicTransportService.models.Account;
import lk.sliit.transport.publicTransportService.models.Card;
import lk.sliit.transport.publicTransportService.repositories.AccountRepository;
import lk.sliit.transport.publicTransportService.repositories.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jonathan on 11/15/2017.
 */
@Service
public class AccountService {


    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CardRepository cardRepository;

    Logger logger = LoggerFactory.getLogger(DaypassService.class);

    public PassengerDTO getPassengerByAccount(Long id){
        logger.info("Retrieving account id " + id + " ...");
        Account account = accountRepository.findOne(id);
        PassengerDTO passengerDTO = new PassengerDTO();

        passengerDTO.setUsername(account.getUsername());
        passengerDTO.setRole(account.getRole());
        passengerDTO.setAccountId(account.getAccountId());
        passengerDTO.setPassengerId(account.getDailyPassenger().getId());
        passengerDTO.setCardNo(account.getCard().getId());
        passengerDTO.setName(account.getDailyPassenger().getName());
        passengerDTO.setEmail(account.getDailyPassenger().getEmail());
        passengerDTO.setAddress(account.getDailyPassenger().getAddress());
        passengerDTO.setCardRef(account.getCard().getTokenRef());
        passengerDTO.setBalance(account.getCard().getBalance());

        return passengerDTO;

    }

    public CardDTO getCardByAccount(long id){
        logger.info("Retrieving account id " + id + " ...");
        Account account = accountRepository.findOne(id);
        CardDTO cardDTO = new CardDTO();
        cardDTO.setCardNo(account.getCard().getId());
        cardDTO.setCardRef(account.getCard().getTokenRef());
        cardDTO.setBalance(account.getCard().getBalance());
        cardDTO.setAccountId(account.getCard().getAccount().getAccountId());
        return cardDTO;
    }
}
