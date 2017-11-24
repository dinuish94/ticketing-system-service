package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.dtos.AccountDTO;
import lk.sliit.transport.publicTransportService.dtos.PassengerDTO;
import lk.sliit.transport.publicTransportService.dtos.UserDTO;
import lk.sliit.transport.publicTransportService.models.Account;
import lk.sliit.transport.publicTransportService.models.Passenger;
import lk.sliit.transport.publicTransportService.repositories.AccountRepository;
import lk.sliit.transport.publicTransportService.repositories.PassengerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jonathan on 11/15/2017.
 */

@Service
public class LoginService {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    AccountRepository accountRepository;

    Logger logger = LoggerFactory.getLogger(LoginService.class);

    public AccountDTO authenticateUser(UserDTO userDTO){
        AccountDTO accountDTO = new AccountDTO();
        logger.info("Retrieving account by tusername " + userDTO.getUsername() + " ...");
        Account account=accountRepository.findByUsername(userDTO.getUsername());
        if(account!=null){
            if(account.getPassword().equals(userDTO.getPassword())){
                accountDTO.setUsername(account.getUsername());
                accountDTO.setRole(account.getRole());
                accountDTO.setAccountId(account.getAccountId());
                accountDTO.setPassengerId(account.getDailyPassenger().getId());
                accountDTO.setCardNo(account.getCard().getId());
            }

        }

        return accountDTO;
    }
}

