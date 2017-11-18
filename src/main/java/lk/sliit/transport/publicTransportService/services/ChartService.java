package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.dtos.ChartDTO;
import lk.sliit.transport.publicTransportService.dtos.TripDTO;
import lk.sliit.transport.publicTransportService.models.BusStop;
import lk.sliit.transport.publicTransportService.models.Trip;
import lk.sliit.transport.publicTransportService.repositories.BusStopRepository;
import lk.sliit.transport.publicTransportService.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kashifroshen on 11/17/17.
 */
@Service
public class ChartService {
    @Autowired
    TripRepository tripRepository;

    @Autowired
    BusStopRepository busStopRepository;

    public HashMap<String, Integer> getChartData(String filter,Long startBusStop, Long endBusStop, String startDate, String endDate) {
        System.out.println("jsfhwuhwswf");
        System.out.println(startBusStop);
        List<Trip> tripList = new ArrayList<>();
        tripList = tripRepository.findByStartBusStop(busStopRepository.findOne(startBusStop));

        HashMap<String, Integer> chartHashMap = new HashMap<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println(tripList);

        tripList.forEach(trip -> {
            if (chartHashMap.containsKey(df.format(trip.getDate()))) {
                System.out.print("sssfsfcs");
                chartHashMap.put(df.format(trip.getDate()),chartHashMap.get(df.format(trip.getDate()))+1);
            } else {
                chartHashMap.put(df.format(trip.getDate()),1);
            }
        });

        return chartHashMap;
    }
}
