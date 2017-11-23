package lk.sliit.transport.publicTransportService.controllers;

import lk.sliit.transport.publicTransportService.dtos.CardDTO;
import lk.sliit.transport.publicTransportService.models.Passenger;
import lk.sliit.transport.publicTransportService.models.TopUp;
import lk.sliit.transport.publicTransportService.models.Trip;
import lk.sliit.transport.publicTransportService.services.CardService;
import lk.sliit.transport.publicTransportService.services.PassengerService;
import lk.sliit.transport.publicTransportService.services.TopupService;
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

    @Autowired
    TopupService topupService;

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

    @GetMapping("{token}/topups")
    @ResponseBody
    public List<TopUp> getTopUpsForPassenger(@PathVariable("token") String token) {
        return topupService.getTopUpsForPassenger(token);
    }

    @GetMapping("{token}/trips")
    @ResponseBody
    public List<Trip> getTripsForPassenger(@PathVariable("token") String token) {
        return passengerService.getTripsForPassenger(token);
    }
}
