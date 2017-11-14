package lk.sliit.transport.publicTransportService.controllers;

import lk.sliit.transport.publicTransportService.dtos.TripDTO;
import lk.sliit.transport.publicTransportService.models.Trip;
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

    @PostMapping("/")
    @ResponseBody()
    public Trip checkin(@RequestBody TripDTO tripDTO){
        return tripService.checkin(tripDTO);
    }

}
