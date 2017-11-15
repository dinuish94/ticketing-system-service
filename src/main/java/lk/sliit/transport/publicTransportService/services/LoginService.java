package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.dtos.AccountDTO;
import lk.sliit.transport.publicTransportService.dtos.PassengerDTO;
import lk.sliit.transport.publicTransportService.dtos.UserDTO;
import lk.sliit.transport.publicTransportService.models.Account;
import lk.sliit.transport.publicTransportService.models.Passenger;
import lk.sliit.transport.publicTransportService.repositories.AccountRepository;
import lk.sliit.transport.publicTransportService.repositories.PassengerRepository;
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

//    public Passenger authenticateUser(UserDTO userDTO){
//        Passenger passenger=passengerRepository.findOne(userDTO.getId());
//        if(passenger.getPassword().equals(userDTO.getPassword())){
//            return passenger;
//        }
//        return null;
//    }

    public AccountDTO authenticateUser(UserDTO userDTO){
        System.out.println(userDTO.getUsername());
        System.out.println(userDTO.getPassword());
        AccountDTO accountDTO = new AccountDTO();

        Account account=accountRepository.findByUsername(userDTO.getUsername());
        System.out.println(account);
        if(account!=null){
            System.out.println("Inside If");
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

