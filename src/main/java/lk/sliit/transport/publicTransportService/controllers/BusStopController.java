package lk.sliit.transport.publicTransportService.controllers;

import lk.sliit.transport.publicTransportService.models.BusStop;
import lk.sliit.transport.publicTransportService.services.BusStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dinukshakandasamanage on 11/16/17.
 */
@RestController
@RequestMapping(path = "/busStops")
@CrossOrigin(origins = "*")
public class BusStopController {

    @Autowired
    BusStopService busStopService;

    @GetMapping("")
    @ResponseBody
    public List<BusStop> getAllBusStops(){
        return busStopService.getAllBusStops();
    }
}
