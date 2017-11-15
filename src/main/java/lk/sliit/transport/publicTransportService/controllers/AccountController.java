package lk.sliit.transport.publicTransportService.controllers;

import lk.sliit.transport.publicTransportService.dtos.CardDTO;
import lk.sliit.transport.publicTransportService.dtos.PassengerDTO;
import lk.sliit.transport.publicTransportService.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jonathan on 11/15/2017.
 */
@RestController
@RequestMapping(path = "/accounts")
@CrossOrigin(origins = "*")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/{accountId}/passengers")
    @ResponseBody()
    public PassengerDTO getPassenger(@PathVariable("accountId") Long accountId){
        return accountService.getPassengerByAccount(accountId);
    }

    @GetMapping("/{accountId}/cards")
    @ResponseBody()
    public CardDTO getCard(@PathVariable("accountId") Long accountId){
        return accountService.getCardByAccount(accountId);
    }
}
