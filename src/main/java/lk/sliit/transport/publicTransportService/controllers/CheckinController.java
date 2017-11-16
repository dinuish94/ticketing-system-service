package lk.sliit.transport.publicTransportService.controllers;

import lk.sliit.transport.publicTransportService.dtos.TripDTO;
import lk.sliit.transport.publicTransportService.exceptions.CardNotFoundException;
import lk.sliit.transport.publicTransportService.exceptions.InvalidAccountException;
import lk.sliit.transport.publicTransportService.models.Passenger;
import lk.sliit.transport.publicTransportService.models.Trip;
import lk.sliit.transport.publicTransportService.services.CardService;
import lk.sliit.transport.publicTransportService.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dinukshakandasamanage on 11/13/17.
 */

@RestController
@RequestMapping(path = "/checkin")
@CrossOrigin(origins = "*")
public class CheckinController {

    @Autowired
    private TripService tripService;

    @Autowired
    private CardService cardService;

    @PostMapping("/")
    @ResponseBody()
    public Trip checkin(@RequestBody TripDTO tripDTO){
        return tripService.checkin(tripDTO);
    }

    @GetMapping("")
    @ResponseBody
    public boolean validateToken(@RequestParam("token") String token){
        return cardService.validateCard(token);
    }

    @GetMapping("/passenger")
    @ResponseBody
    public Passenger findPassengerByToken(@RequestParam("token") String token) throws CardNotFoundException, InvalidAccountException {
        return cardService.findPassengerByToken(token);
    }

}
