package lk.sliit.transport.publicTransportService.controllers;

import lk.sliit.transport.publicTransportService.dtos.AccountDTO;
import lk.sliit.transport.publicTransportService.dtos.PassengerDTO;
import lk.sliit.transport.publicTransportService.dtos.UserDTO;
import lk.sliit.transport.publicTransportService.models.Account;
import lk.sliit.transport.publicTransportService.models.Passenger;
import lk.sliit.transport.publicTransportService.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jonathan on 11/15/2017.
 */

@RestController
@RequestMapping(path = "/login")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    LoginService loginService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/")
    @ResponseBody()
    public AccountDTO authenticate(@RequestBody UserDTO userDTO) {
        logger.info("Checking username" + userDTO.getUsername() + "... ");
        return loginService.authenticateUser(userDTO);
    }

}
