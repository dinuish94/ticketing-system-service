package lk.sliit.transport.publicTransportService;

import lk.sliit.transport.publicTransportService.models.*;
import lk.sliit.transport.publicTransportService.repositories.*;
import lk.sliit.transport.publicTransportService.services.DaypassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by dinukshakandasamanage on 11/11/17.
 */

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BusRepository busRepository;

    @Autowired
    BusStopRepository busStopRepository;

    @Autowired
    BusCategoryRepository busCategoryRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    DaypassRepository daypassRepository;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        Card card4 = new Card();
        card4.setBalance(50);
        System.out.println(cardRepository.save(card4).getTokenRef());

        Visitor passenger = new Visitor();
        passenger.setName("Peter Parker");
        passenger.setAddress("Negombo");
        passenger.setCard(card4);
        passengerRepository.save(passenger);

        Card card = new Card();
        card.setBalance(200);
        card.setVisitor(passenger);
        card = cardRepository.save(card);

        Daypass daypass = new Daypass();
        daypass.setDate(new Date());
        List<Daypass> daypasses = new ArrayList<>();
        daypass.setCard(card);
        daypass = daypassRepository.save(daypass);
        daypasses.add(daypass);

        card.setDaypasses(daypasses);

        card = cardRepository.save(card);
        System.out.println(card.getTokenRef());


        Account account = new Account();
        account.setCard(card);
        account.setRole(1);
        account.setUsername("Bruce");
        account.setPassword("123");
        accountRepository.save(account);

        DailyPassenger passenger2 = new DailyPassenger();
        passenger2.setName("Bruce Wayne");
        passenger2.setAddress("Gotham");
        passenger2.setAccount(account);
        passenger2.setEmail("bruce@gmail.com");
        passengerRepository.save(passenger2);

        Card card2 = new Card();
        card2.setBalance(300);
        System.out.println(cardRepository.save(card2).getTokenRef());

        Account account2 = new Account();
        account2.setCard(card2);
        account2.setRole(1);
        account2.setUsername("Jonathan");
        account2.setPassword("123");
        account2 = accountRepository.save(account2);

        DailyPassenger passenger3 = new DailyPassenger();
        passenger3.setName("Jonathan");
        passenger3.setAddress("Colombo");
        passenger3.setAccount(account2);
        passenger3.setEmail("jonathan@gmail.com");
        passengerRepository.save(passenger3);

        passengerRepository.findAll().forEach(passenger1 -> {
            System.out.println(passenger1.toString());
        });

        BusCategory busCategory = new BusCategory();
        busCategory.setRate(20);
        busCategory.setType("Luxury");
        busCategory = busCategoryRepository.save(busCategory);

        Bus bus = new Bus();
        bus.setBusCategory(busCategory);
        bus = busRepository.save(bus);
        System.out.println(bus.getId());

        BusStop busStop = new BusStop();
        busStop.setLocation("Kolpitty");
        System.out.println(busStopRepository.save(busStop));

        BusStop busStop1 = new BusStop();
        busStop1.setLocation("Bambalapitiya");
        System.out.println(busStopRepository.save(busStop1));

        BusStop busStop2 = new BusStop();
        busStop2.setLocation("Galle");
        System.out.println(busStopRepository.save(busStop2));

        BusStop busStop3 = new BusStop();
        busStop3.setLocation("Kandy");
        System.out.println(busStopRepository.save(busStop3));

        Trip trip = new Trip();
        trip.setBus(bus);
        trip.setStartBusStop(busStop);
        trip.setEndBusStop(busStop1);
        trip.setStartTime("00:30");

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String datestring = "2017/11/22 00:00";
        Date date = format.parse(datestring);

        trip.setDate(date);

        Trip trip1 = new Trip();
        trip1.setBus(bus);
        trip1.setStartBusStop(busStop);
        trip1.setEndBusStop(busStop1);
        trip1.setStartTime("08:30");

        String datestring1 = "2017/11/22 00:00";
        Date date1 = format.parse(datestring1);

        trip1.setDate(date1);

        Trip trip2 = new Trip();
        trip2.setBus(bus);
        trip2.setStartBusStop(busStop);
        trip2.setEndBusStop(busStop1);
        trip2.setStartTime("11:00");

        String datestring2 = "2017/11/22 00:00";
        Date date2 = format.parse(datestring2);

        trip2.setDate(date2);

        Trip trip3 = new Trip();
        trip3.setBus(bus);
        trip3.setStartBusStop(busStop);
        trip3.setEndBusStop(busStop1);
        trip3.setStartTime("15:30");

        String datestring3 = "2017/11/22 00:00";
        Date date3 = format.parse(datestring3);

        trip3.setDate(date3);

        Trip trip4 = new Trip();
        trip4.setBus(bus);
        trip4.setStartBusStop(busStop);
        trip4.setEndBusStop(busStop1);
        trip4.setStartTime("22:00");

        String datestring5 = "2017/11/21 00:00";
        Date date5 = format.parse(datestring5);

        trip4.setDate(date5);

        tripRepository.save(trip);

        tripRepository.save(trip1);

        tripRepository.save(trip2);

        tripRepository.save(trip3);

        tripRepository.save(trip4);



//        System.out.println(busRepository.findOne(bus.getId()).getId());
    }
}