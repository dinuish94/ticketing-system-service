package lk.sliit.transport.publicTransportService.controllers;

import lk.sliit.transport.publicTransportService.dtos.TripDTO;
import lk.sliit.transport.publicTransportService.models.Trip;
import lk.sliit.transport.publicTransportService.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Handles journey checkouts
 *
 * Created by dinukshakandasamanage on 11/13/17.
 */

@RestController
@RequestMapping(path = "/checkout")
@CrossOrigin(origins = "*")
public class CheckoutController {

    @Autowired
    private TripService tripService;

    @PostMapping("/")
    @ResponseBody()
    public Trip checkout(@RequestBody TripDTO tripDTO){
        return tripService.checkout(tripDTO);
    }
}
