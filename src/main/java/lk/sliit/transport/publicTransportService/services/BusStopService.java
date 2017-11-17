package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.models.BusStop;
import lk.sliit.transport.publicTransportService.repositories.BusStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dinukshakandasamanage on 11/16/17.
 */
@Service
public class BusStopService {

    @Autowired
    BusStopRepository busStopRepository;

    public List<BusStop> getAllBusStops(){
        List<BusStop> busStops = new ArrayList<>();
        busStopRepository.findAll().forEach( busStop-> {
            busStops.add(busStop);
        });
        return busStops;
    }
}
