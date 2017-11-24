package lk.sliit.transport.publicTransportService.services;

import lk.sliit.transport.publicTransportService.dtos.BarChartDTO;
import lk.sliit.transport.publicTransportService.dtos.ChartDTO;
import lk.sliit.transport.publicTransportService.dtos.DefaultChartDTO;
import lk.sliit.transport.publicTransportService.dtos.TripDTO;
import lk.sliit.transport.publicTransportService.models.Bus;
import lk.sliit.transport.publicTransportService.models.BusStop;
import lk.sliit.transport.publicTransportService.models.Trip;
import lk.sliit.transport.publicTransportService.repositories.BusStopRepository;
import lk.sliit.transport.publicTransportService.repositories.TripRepository;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
        List<Trip> tripList = new ArrayList<>();
        tripList = tripRepository.findByStartBusStop(busStopRepository.findOne(startBusStop));

        HashMap<String, Integer> chartHashMap = new HashMap<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        tripList.forEach(trip -> {
            if (chartHashMap.containsKey(df.format(trip.getDate()))) {
                chartHashMap.put(df.format(trip.getDate()),chartHashMap.get(df.format(trip.getDate()))+1);
            } else {
                chartHashMap.put(df.format(trip.getDate()),1);
            }
        });

        return chartHashMap;
    }


//    public HashMap<String, Integer> getChartDataByBusStop(Long startBusStopId, Long endBusStopId) {
//        List<Trip> trips = new ArrayList<>();
//
//        if (startBusStopId != null && endBusStopId != null) {
//            BusStop startBusStop = busStopRepository.findOne(startBusStopId);
//            BusStop endBusStop = busStopRepository.findOne(endBusStopId);
//            trips = tripRepository.findByStartBusStopAndEndBusStop(startBusStop,endBusStop);
//        } else if (startBusStopId != null) {
//            BusStop startBusStop = busStopRepository.findOne(startBusStopId);
//            trips = tripRepository.findByStartBusStop(startBusStop);
//        } else {
//            BusStop endBusStop = busStopRepository.findOne(endBusStopId);
//            trips = tripRepository.findByEndBusStop(endBusStop);
//        }
//
//        return MapDataForBusStop(trips);
//    }

    public List<BarChartDTO> MapDataForBusStop(List<Trip> trips) {
        HashMap<String, Integer> chartHashMap = new HashMap<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        List<BarChartDTO> barChartData = new ArrayList<>();

        String time = "";

        for(int i=0;i<=23;i++) {
                if (i<10) {
                    time = "0" + i;
                } else {
                    time = "" + i;
                }
            BarChartDTO barChart = new BarChartDTO();
            BarChartDTO barChart1 = new BarChartDTO();
            barChart.setDate(time+":00");
            barChart.setCount(0);
            barChartData.add(barChart);

            barChart1.setDate(time+":30");
            barChart1.setCount(0);
            barChartData.add(barChart1);
        }

        trips.forEach(trip -> {

            for(int i=0;i<barChartData.size();i++) {

                if (barChartData.get(i).getDate().equals(trip.getStartTime())) {
                    BarChartDTO barChartDTO = new BarChartDTO();

                    if (barChartData.get(i).getCount() > 0) {
                        barChartDTO.setDate(barChartData.get(i).getDate());
                        barChartDTO.setCount(barChartData.get(i).getCount()+1);
                    } else {
                        barChartDTO.setDate(barChartData.get(i).getDate());
                        barChartDTO.setCount(1);
                    }
                    barChartData.remove(i);
                    barChartData.add(i,barChartDTO);
                }
            }

        });
        return barChartData;
    }

    public DefaultChartDTO defaultData(String date) {
        int number=0;
        List<BarChartDTO> trips = new ArrayList<>();
        List<Object[]> startBusStops = new ArrayList<>();
        List<Object[]> endBusStops = new ArrayList<>();

        if (date.equals("yesterday")) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = df.format(getDate(1));
            Date day = new Date();
            try {
                day = df.parse(dateString);
            } catch(Exception e) {
                System.out.print(e);
            }
            trips = MapDataForBusStop(getTripsForDate(getDate(1)));
            startBusStops = tripRepository.getStartBusStopCountForDate(day);
            endBusStops = tripRepository.getEndBusStopCountForDate(day);
        } else if (date.equals("last 7 days")) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = df.format(getDate(7));
            String dateString1 = df.format(getDate(0));
            Date day1 = new Date();
            Date day2 = new Date();
            try {
                day1 = df.parse(dateString);
                day2 = df.parse(dateString1);
            } catch(Exception e) {
                System.out.print(e);
            }
            trips = MapDataForBusStop(getTripsForDateRange(getDate(7),getDate(0)));
            startBusStops = tripRepository.getStartBusStopCountForDateRange(day1,day2);
            endBusStops = tripRepository.getEndBusStopCountForDateRange(day1,day2);
        } else {
            Date day = new Date();

            try {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                day = df.parse(date);

                trips = MapDataForBusStop(getTripsForDate(day));
                startBusStops = tripRepository.getStartBusStopCountForDate(day);
                endBusStops = tripRepository.getEndBusStopCountForDate(day);
            } catch(Exception e) {

            }
        }

        DefaultChartDTO defaultChart = new DefaultChartDTO();

        defaultChart.setYesterdayRecords(trips);
        defaultChart.setStartBusStopRecords(startBusStops);
        defaultChart.setEndBusStopRecords(endBusStops);

        return defaultChart;
    }

//    public List<Object[]> getStartBusStopsForYesterday() {
//        return tripRepository.getStartBusStopCount();
//    }

    public List<Trip> getTripsForDate(Date day) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = df.format(day);
        Date date = new Date();

        try {
            date = df.parse(dateString);
        } catch(Exception e) {
            System.out.print(e);
        }

        return  tripRepository.findByDate(date);
    }

    public List<Trip> getTripsForDateRange(Date day1,Date day2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateString1 = df.format(day1);
        String dateString2 = df.format(day2);

        Date date1 = new Date();
        Date date2 = new Date();

        try {
            date1 = df.parse(dateString1);
            date2 = df.parse(dateString2);
        } catch(Exception e) {
            System.out.print(e);
        }

        return  tripRepository.findByDateBetween(date1,date2);
    }

    private Date getDate(int number) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -number);
        return cal.getTime();
    }

    private Date last7days() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }



}
