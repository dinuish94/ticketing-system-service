package lk.sliit.transport.publicTransportService.controllers;

import lk.sliit.transport.publicTransportService.dtos.TripDTO;
import lk.sliit.transport.publicTransportService.models.Trip;
import lk.sliit.transport.publicTransportService.services.CheckinService;
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
    private CheckinService checkinService;

    @PostMapping("/")
    @ResponseBody()
    public Trip getAllAssignments(@RequestBody TripDTO tripDTO){
        return checkinService.checkin(tripDTO);
    }

}
