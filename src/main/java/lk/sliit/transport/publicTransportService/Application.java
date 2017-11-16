package lk.sliit.transport.publicTransportService;

import lk.sliit.transport.publicTransportService.models.*;
import lk.sliit.transport.publicTransportService.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Visitor passenger = new Visitor();
        passenger.setName("Visitor 1");

        passenger = passengerRepository.save(passenger);

        passengerRepository.findAll().forEach(passenger1 -> {
            System.out.println(passenger1.toString());
        });

        Card card = new Card();
        card.setBalance(200);
        card.setVisitor(passenger);
        card = cardRepository.save(card);
        System.out.println(card.getTokenRef());

        passenger.setCard(card);
        passenger = passengerRepository.save(passenger);

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

//        System.out.println(busRepository.findOne(bus.getId()).getId());
    }
}