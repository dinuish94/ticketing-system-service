package lk.sliit.transport.publicTransportService.controllers;

import lk.sliit.transport.publicTransportService.dtos.JourneyDTO;
import lk.sliit.transport.publicTransportService.models.Trip;
import lk.sliit.transport.publicTransportService.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by dinukshakandasamanage on 11/17/17.
 */

@RestController
@CrossOrigin(origins = "*")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping("/buses/{busId}/trips")
    @ResponseBody
    public List<JourneyDTO> getTripsForBus(@PathVariable long busId){
        return tripService.getTripsForBus(busId);
    }

    @GetMapping("trips")
    @ResponseBody
    public List<Trip> getTripsForCard(@RequestParam(value="token", defaultValue = "") String token) {
        return tripService.getTripsByCard(token);
    }

}
