package lk.sliit.transport.publicTransportService.controllers;

import lk.sliit.transport.publicTransportService.dtos.CardDTO;
import lk.sliit.transport.publicTransportService.models.Passenger;
import lk.sliit.transport.publicTransportService.services.CardService;
import lk.sliit.transport.publicTransportService.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Jonathan on 11/15/2017.
 */
@RestController
@RequestMapping(path = "/passengers")
@CrossOrigin(origins = "*")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @Autowired
    CardService cardService;

    @GetMapping("")
    @ResponseBody()
    public List<Passenger> addDayPass(){
        return passengerService.getAllPassengers();
    }

    @GetMapping("visitors/{cardRef}/cards")
    @ResponseBody()
    public CardDTO getCard(@PathVariable("cardRef") String cardRef){
        return cardService.getCard(cardRef);
    }

    @GetMapping("/{token}")
    @ResponseBody()
    public Lisr<Trip> findTrips (@PathVariable("token") String token){
        return  passengerService.getTripsForPassenger(cardService.token);
    }
}
