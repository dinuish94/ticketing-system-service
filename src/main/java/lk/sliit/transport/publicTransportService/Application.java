package lk.sliit.transport.publicTransportService;

import lk.sliit.transport.publicTransportService.models.*;
import lk.sliit.transport.publicTransportService.repositories.BusRepository;
import lk.sliit.transport.publicTransportService.repositories.BusStopRepository;
import lk.sliit.transport.publicTransportService.repositories.CardRepository;
import lk.sliit.transport.publicTransportService.repositories.PassengerRepository;
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

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Passenger passenger = new Visitor();
        passenger.setName("Visitor 1");

        passengerRepository.save(passenger);

        passengerRepository.findAll().forEach(passenger1 -> {
            System.out.println(passenger1.toString());
        });

        Card card = new Card();

        System.out.println(cardRepository.save(card).getTokenRef());

        Bus bus = new Bus();
        bus = busRepository.save(bus);
        System.out.println(bus.getId());

        BusStop busStop = new BusStop();
        busStop.setLocation("Kolpitty");
        System.out.println(busStopRepository.save(busStop));

//        System.out.println(busRepository.findOne(bus.getId()).getId());
    }
}