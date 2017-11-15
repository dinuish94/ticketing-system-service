package lk.sliit.transport.publicTransportService.controllers;

import lk.sliit.transport.publicTransportService.dtos.AccountDTO;
import lk.sliit.transport.publicTransportService.dtos.PassengerDTO;
import lk.sliit.transport.publicTransportService.dtos.UserDTO;
import lk.sliit.transport.publicTransportService.models.Account;
import lk.sliit.transport.publicTransportService.models.Passenger;
import lk.sliit.transport.publicTransportService.services.LoginService;
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

    @PostMapping("/")
    @ResponseBody()
    public AccountDTO authenticate(@RequestBody UserDTO userDTO) {
        System.out.println("HERE");
        return loginService.authenticateUser(userDTO);
    }

}
