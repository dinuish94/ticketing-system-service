package lk.sliit.transport.publicTransportService.controllers;

import lk.sliit.transport.publicTransportService.dtos.DefaultChartDTO;
import lk.sliit.transport.publicTransportService.models.BusStop;
import lk.sliit.transport.publicTransportService.models.Trip;
import lk.sliit.transport.publicTransportService.services.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kashifroshen on 11/17/17.
 */
@RestController
@RequestMapping(path = "/charts")
@CrossOrigin(origins = "*")
public class ChartController {

    @Autowired
    ChartService chartService;



//    @GetMapping("")
//    @ResponseBody

//    public HashMap<String, Integer> getChartData(@RequestParam(value="day", defaultValue = "") String day,@RequestParam(value="filter", defaultValue = "") String filter,
//                                                 @RequestParam(value="startBusStop", defaultValue = "") Long startBusStop,
//                                                 @RequestParam(value="endBusStop", defaultValue = "") Long endBusStop,
//                                                 @RequestParam(value="startDate", defaultValue = "") String startDate,
//                                                 @RequestParam(value="endDate", defaultValue = "") String endDate) {
////        if (day != null) {
////            return chartService.defaultData(day);
////        }
//        return chartService.getChartDataByBusStop(startBusStop, endBusStop);
//    }

    @GetMapping("")
    @ResponseBody

    public DefaultChartDTO getDefaultChartData(@RequestParam(value="day", defaultValue = "") String day) {
        return chartService.defaultData(day);
    }
}
